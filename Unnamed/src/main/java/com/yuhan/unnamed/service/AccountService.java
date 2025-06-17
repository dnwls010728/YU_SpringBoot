package com.yuhan.unnamed.service;

import com.yuhan.unnamed.domain.UserInfo;
import com.yuhan.unnamed.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Transactional
    public boolean join(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        var user = new UserInfo();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return true;
    }
}
