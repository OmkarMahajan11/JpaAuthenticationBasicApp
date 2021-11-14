package com.example.springsecurityjpa;

import java.util.Optional;

import com.example.springsecurityjpa.models.MyUserDetails;
import com.example.springsecurityjpa.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(username);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    return user.map(MyUserDetails::new).get();
  }
}
