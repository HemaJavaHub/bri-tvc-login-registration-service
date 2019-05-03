package com.authenticate.loginservice.controllers;

import com.authenticate.loginservice.models.User;
import com.authenticate.loginservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;




    @CrossOrigin("*")
    @GetMapping("users/login")
    public User validateUser(@RequestHeader("email") String email, @RequestHeader("password")String password){
        return  userService.validateUserLogin(email, password);

    }


    @CrossOrigin("*")
    @PostMapping("users/registration")
    public @ResponseBody String registerUser(@RequestBody User user){
        System.out.println("new User encrypted password:"+user.getPassword());
        return userService.registerUser(user);

    }
}
