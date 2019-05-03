package com.authenticate.loginservice.services;

import com.authenticate.loginservice.Repositories.UserRepository;
import com.authenticate.loginservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    public User validateUserLogin(String email, String password){
        String jwtToken = "";
        System.out.println(email);
      User user= userRepository.findByEmail(email);
        System.out.println("user:"+user);
      /*if(bCryptPasswordEncoder.encode(password).equals(user.getPassword())) */
        if(user!=null && user.getPassword().equals((password))){
          System.out.println("inside email chaeck ..."+user.getPassword());
          jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
                  .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
          user.setToken(jwtToken);
          return user;
      }
      else
          return null;

    }



    public String registerUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("new User encrypted password:"+user.getPassword());
        if(userRepository.findByEmail(user.getEmail())!=null)
            return "user already exists!";
        userRepository.save(user);
        return "success";

    }
}
