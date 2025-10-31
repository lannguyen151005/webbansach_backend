package com.lannguyen.webbansach_backend.security;

public class Endpoints {

    public static final String[] PUBLIC_GET_ENGPOINTS = {
            "/books",
            "/books/**",
            "/images",
            "/images/**",
            "/users/search/existsByUsername",
            "/users/search/existsByEmail",
            "/account/activate/**"
    };

    public static final String[] PUBLIC_POST_ENGPOINTS = {
            "/account/register"
    };

    public static final String[] ADMIN_GET_ENGPOINTS = {
            "/users",
            "/users/**"
    };
    public static final String front_end_host = "http://localhost:3000";
}
