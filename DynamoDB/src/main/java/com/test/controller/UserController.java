package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.UserService;

@RestController
@RequestMapping("/test")
public class UserController {
	@Autowired
    private  UserService userService;


    @GetMapping("/{id}")
    public String testDynamo(@PathVariable String id) {
        return userService.getUser(id);
    }
}
