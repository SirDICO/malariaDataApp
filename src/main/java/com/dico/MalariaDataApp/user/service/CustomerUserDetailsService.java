package com.dico.MalariaDataApp.user.service;

import com.dico.MalariaDataApp.user.model.User;
import com.dico.MalariaDataApp.user.model.UserPrincipal;
import com.dico.MalariaDataApp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not Found");

        }
        return new UserPrincipal(user);
    }
}
