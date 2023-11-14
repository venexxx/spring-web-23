package com.example.interceptor;



import com.example.model.dto.BrandBindingModel;
import com.example.model.entity.UserEntity;
import com.example.service.BrandService;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

public class CustomInterceptor implements HandlerInterceptor {


   private final BrandService brandService;
   private final UserService userService;

    public CustomInterceptor(BrandService brandService,UserService userService) {
        this.brandService = brandService;
        this.userService = userService;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Log information before the request is handled by the controller
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Method: " + request.getMethod());

        if (isItBanned(request)) {
            // If there is having brands, you can redirect them to a brands page
            //response.sendRedirect("/users/login");
             // Stop the request processing
           if (!request.getRequestURI().equals("/users/banned")) {
                // Redirect the user to a banned page
               response.sendRedirect("/users/banned");




                 // Stop the request processing
               return false;
           }
            invalidateUserSession(request);


        }
        return true;
        // Continue with the request handling
    }
    private void invalidateUserSession(HttpServletRequest request) {
        // Invalidate the user's session (logout)
        HttpSession session = request.getSession(false); // do not create a new session if none exists
        if (session != null) {
            session.invalidate();
        }
    }
    private boolean isItBanned(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


        String name = auth.getName();
        UserEntity isBannedUser = userService.getByUserEmail(name);
        if (isBannedUser != null && isBannedUser.isBanned() && isBannedUser.isBanned()){
            return true;
        }

        return false;
    }


}