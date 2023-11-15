package com.example.config;


import com.example.model.entity.enums.UserRoleEnum;
import com.example.repository.UserRepository;
import com.example.service.impl.AutoSellerUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration   {



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.authorizeHttpRequests(
        // Define which urls are visible by which users
        authorizeRequests -> authorizeRequests
            // All static resources which are situated in js, images, css are available for anyone
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            // Allow anyone to see the home page, the registration page and the login form
            .requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll()
            .requestMatchers("/offers/all").permitAll()
            .requestMatchers("/offers/catalog").permitAll()
            .requestMatchers(HttpMethod.GET, "/offer/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/offer/**").authenticated()
            .requestMatchers("/error").permitAll()
            .requestMatchers("/brands").authenticated()
            .requestMatchers("/users/banned").permitAll()
            .requestMatchers("/users/all").hasRole(UserRoleEnum.ADMIN.name())
            .requestMatchers("/users/unban-user/**").hasRole(UserRoleEnum.ADMIN.name())
            .requestMatchers("/users/ban-user/**").hasRole(UserRoleEnum.ADMIN.name())
            .requestMatchers("/users/details/**").permitAll()
            .requestMatchers("/brands/add").hasRole(UserRoleEnum.ADMIN.name())
            .requestMatchers("/brands").hasRole(UserRoleEnum.VIP.name())


            // all other requests are authenticated.
            .anyRequest().authenticated()
    ).formLogin(
        formLogin -> {
          formLogin
              // redirect here when we access something which is not allowed.
              // also this is the page where we perform login.
              .loginPage("/users/login")
              // The names of the input fields (in our case in auth-login.html)
              .usernameParameter("email")
              .passwordParameter("password")
              .defaultSuccessUrl("/")
              .failureForwardUrl("/users/login-error");
        }
    ) .logout(
        logout -> {
          logout
              // the URL where we should POST something in order to perform the logout
              .logoutUrl("/users/logout")
              // where to go when logged out?
              .logoutSuccessUrl("/")
              // invalidate the HTTP session
              .invalidateHttpSession(true);
        }).build();

//    .rememberMe(
//        rememberMe -> {
//          rememberMe
//              .key(rememberMeKey)
//              .rememberMeParameter("rememberme")
//              .rememberMeCookieName("rememberme");
//        }
//    )

  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    // This service translates the mobilele users and roles
    // to representation which spring security understands.
    return new AutoSellerUserDetailsService(userRepository);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
  }

}