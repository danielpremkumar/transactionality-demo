package com.daniel.transactionalitydemo.runner;

import com.daniel.transactionalitydemo.model.User;
import com.daniel.transactionalitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class UserRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        try {
            User user1 = new User("Dave", "Dev", 156000L);
            User user2 = new User("Tom", "QA", 0L);
            User user3 = new User("Paul", "HR", 16000L);
            User user4 = new User("Jack", "Business", 6000L);
            userService.insert(Arrays.asList(user1, user2, user3, user4));
        } catch (RuntimeException exception) {
            System.out.println("Exception occurred..." + exception);
        }
        System.out.println(userService.getUsers());

    }
}
