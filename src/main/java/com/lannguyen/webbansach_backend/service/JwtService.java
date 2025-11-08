package com.lannguyen.webbansach_backend.service;

import com.lannguyen.webbansach_backend.entity.Role;
import com.lannguyen.webbansach_backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    //Lấy từ biến môi trường
    //IntelliJ: Run->Edit Configuration-> trong mục Environment Variables thêm: JWT_SCRET=DA5D5AD51FA1FA1BDS51FAS5DA......
    public static final String SECRET = System.getenv("JWT_SECRET");

    @Autowired
    private UserService userService;

    // Tạo JWT dựa trên username
    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();

        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isUser = false;

        User user = userService.findByUsername(username);
        if(user!=null && user.getRoleList().size()>0){
            List<Role> roleList = user.getRoleList();
            for(Role role : roleList){
                if(role.getName().equals("ADMIN")){
                    isAdmin = true;
                }
                if(role.getName().equals("ADMIN")){
                    isStaff = true;
                }
                if(role.getName().equals("ADMIN")){
                    isUser = true;
                }
            }
        }
        claims.put("isAdmin", isAdmin);
        claims.put("isStaff", isStaff);
        claims.put("isUser", isUser);

        return createToken(claims, username);
    }

    //Tạo JWT với các claims đã chọn
    private String createToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+30*60*1000))//JWT hết hạn sau 30 phút
                .signWith(SignatureAlgorithm.HS256, getSignatureKey())
                .compact();
    }

    private Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Trích xuất thông tin
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSignatureKey()).parseClaimsJws(token).getBody();
    }

    //Trích xuất thông tin cho 1 claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    //Kiểm tra thời gian hết hạn JWT
    public java.util.Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //Kiểm tra JWT đã hết hạn
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new java.util.Date());
    }

    //Kiểm tra tinhs hợp lệ
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

}
