package ku.restaurant.controller;

import jakarta.validation.Valid;
import ku.restaurant.dto.LoginRequest;
import ku.restaurant.dto.SignUpRequest;
import ku.restaurant.entity.User;
import ku.restaurant.security.JwtUtil;
import ku.restaurant.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest) {

        System.out.println("Login Request: " + loginRequest);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User myUser = userService.getUserByName(userDetails.getUsername());
        logger.info("User: {}has been successfully logged in as {}", userDetails.getUsername(), myUser.getRole());

        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpRequest request) {

        if (userService.userExist(request.getUsername())) {
            return new ResponseEntity<>("Error username already taken", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}
