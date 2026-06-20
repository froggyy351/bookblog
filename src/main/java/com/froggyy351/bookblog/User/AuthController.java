package com.froggyy351.bookblog.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    @PostMapping("/api/auth/login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request.getUserName(), request.getPassword());
    }
}
