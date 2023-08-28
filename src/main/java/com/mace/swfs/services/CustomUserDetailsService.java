package com.mace.swfs.services;


import com.mace.swfs.persistance.entities.User;
import com.mace.swfs.persistance.repositories.UserRepository;
import com.mace.swfs.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> user = userRepository.findByEmailAndIsDeleted(email, Constants.NOT_DELETED);
        if (user.isPresent()) {
            Set<GrantedAuthority> authorities = user.get().getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getAuthority().name()))
                    .collect(Collectors.toSet());
            return buildUserForAuthentication(user.get(), new ArrayList<>(authorities));
        }
        return null;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}

