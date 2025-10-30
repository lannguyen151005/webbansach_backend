package com.lannguyen.webbansach_backend.service;

import com.lannguyen.webbansach_backend.dao.RoleRepository;
import com.lannguyen.webbansach_backend.dao.UserRepository;
import com.lannguyen.webbansach_backend.entity.Role;
import com.lannguyen.webbansach_backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Account doesn't exists!");
        }

        org.springframework.security.core.userdetails.User _user = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roleSToAuthoritites(user.getRoleList())
        );
        return _user;
    }
    private Collection<? extends GrantedAuthority> roleSToAuthoritites(Collection<Role> roles){
        return roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
}
