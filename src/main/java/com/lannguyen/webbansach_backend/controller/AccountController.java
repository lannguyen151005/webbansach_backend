package com.lannguyen.webbansach_backend.controller;

import com.lannguyen.webbansach_backend.entity.User;
import com.lannguyen.webbansach_backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @CrossOrigin(origins = "http://localhost:3000") // Cho phep cac request tu localhost:3000
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User user){

        ResponseEntity<?> response = accountService.registerUser(user);

        return response;
    }

    @GetMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestParam String email, @RequestParam String activateCode){

        ResponseEntity<?> response = accountService.activateAccount(email, activateCode);

        return response;
    }
}
