package com.example.init;

import com.example.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BannedUserCleanupJob {
    private final UserService userService;

    public BannedUserCleanupJob(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs at midnight and noon every day
    public void deleteBannedUsers() {
       List<String> userNames = userService.deleteBannedUsers();
        for (String userName : userNames) {
            System.out.println("Deleting banned user: " + userName);
        }
        System.out.println("Deleting banned users...");
    }
}