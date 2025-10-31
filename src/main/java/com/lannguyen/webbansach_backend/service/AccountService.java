package com.lannguyen.webbansach_backend.service;

import com.lannguyen.webbansach_backend.dao.UserRepository;
import com.lannguyen.webbansach_backend.entity.Announce;
import com.lannguyen.webbansach_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> registerUser(User user){

        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body(new Announce("Username existed!"));
        }

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body(new Announce("Email existed!"));
        }

        String encryptedPassword = passwordEncoder.encode((user.getPassword()));
        user.setPassword(encryptedPassword);

        //Gán và gửi thông tin kích hoạt
        user.setActivateCode(createActivateCode());
        user.setActivate(false);

        userRepository.save(user);

        //Gửi email cho người dùng kích hoạt
        sendActivateEmail(user.getEmail(), user.getActivateCode());

        return ResponseEntity.ok("Registered successfully!");
    }

    private String createActivateCode(){
        //Tạo mã ngẫu nhiên
        return UUID.randomUUID().toString();
    }

    private void sendActivateEmail(String email, String activateCode){
        String subject = "Activate your account in Alan.vn";
        String url = "http://localhost:3000/activate/"+email+"/"+activateCode;
        String text = "Please, use this code to activate your account <"+email
                +">:<html><body><br><h1>"+activateCode
                +"<br>Click here to activate: "
                + "<a href="+url+">"
                + url
                +"<a/><h1/><body/><html/>";
        emailService.sendMessage("thuspamcamnghich@gmail.com", email, subject, text);
    }

    public ResponseEntity<?> activateAccount(String email, String activateCode){

        User user = userRepository.findByEmail(email);

        if(user==null){
            return ResponseEntity.badRequest().body(new Announce("User doesn't exist"));
        }

        if(user.isActivate()){
            return ResponseEntity.badRequest().body(new Announce("User is activated"));
        }

        if (activateCode.equals(user.getActivateCode())){
            user.setActivate(true);
            userRepository.save(user);
            return ResponseEntity.ok("Activated successfully");
        }else{
            return ResponseEntity.badRequest().body(new Announce("Activate code doesn't match"));
        }
    }


}
