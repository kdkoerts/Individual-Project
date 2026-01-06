package nl.sogyo.ip.web.controllers;

import nl.sogyo.ip.application.dto.LoginRequest;
import nl.sogyo.ip.application.UserService;
import nl.sogyo.ip.application.dto.UserRegistrationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean ok = userService.login(request.username(), request.password());
        if (ok) {
            return ResponseEntity.ok("Logged in!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDTO dto) {
        try {
            // Let the service handle hashing and creation
            userService.register(dto);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
