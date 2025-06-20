package com.yuhan.unnamed.controller;

import com.yuhan.unnamed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "index";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password)
    {
        if (userService.register(username, password)) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User registration failed");
        }
    }
}
