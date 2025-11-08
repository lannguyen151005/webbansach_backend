package com.lannguyen.webbansach_backend.controller;

import com.lannguyen.webbansach_backend.entity.User;
import com.lannguyen.webbansach_backend.security.JwtResponse;
import com.lannguyen.webbansach_backend.security.LoginRequest;
import com.lannguyen.webbansach_backend.service.AccountService;
import com.lannguyen.webbansach_backend.service.JwtService;
import com.lannguyen.webbansach_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") // Cho phep cac request tu localhost:3000
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

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

    @PostMapping("/log-in")
    public ResponseEntity<?> logIn(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            // Nếu xác thực thành công, tạo token JWT
            if(authentication.isAuthenticated()){
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Username or password mismatch.");
        }
        return ResponseEntity.badRequest().body("Fail to authenticate.");
    }
}
