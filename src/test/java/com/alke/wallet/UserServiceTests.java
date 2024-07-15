package com.alke.wallet;

import com.alke.wallet.model.User;
import com.alke.wallet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void testUserRegistration() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        userService.save(user);
        assertNotNull(userService.loadUserByUsername("testuser"));
    }
}
