package com.lannguyen.webbansach_backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailsSender;


    @Override
    public void sendMessage(String from, String to, String subject, String text) {
        //MimeMailMessage => gửi mail có đính kèm file media
        //SimpleMailMessafe => gưửi mail chỉ có văn bản

        MimeMessage message = emailsSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
        }catch (MessagingException e){
            throw new RuntimeException(e);
        }

        // Thực hiện gửi email
        emailsSender.send(message);
    }
}
