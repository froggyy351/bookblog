package com.froggyy351.bookblog.User;

import com.froggyy351.bookblog.common.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String login(String username, String password){

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        if(bCryptPasswordEncoder.matches(password, user.getPassword())){
            return jwtUtil.generateToken(username);
        }else{
            throw new BadCredentialsException("Invalid password");
        }
    }

}
