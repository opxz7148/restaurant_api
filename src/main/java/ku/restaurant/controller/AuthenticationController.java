package ku.restaurant.controller;

import ku.restaurant.dto.SignUpRequest;
import ku.restaurant.entity.User;
import ku.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String registerUser(@RequestBody SignUpRequest request) {

        if (userService.userExist(request.getUsername())) {
            return "Error: Username is already taken!";
        }

        userService.createUser(request);
        return "User registered successfully!";
    }
}
