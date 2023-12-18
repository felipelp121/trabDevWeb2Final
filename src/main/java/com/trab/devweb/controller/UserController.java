package com.trab.devweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;
import com.trab.devweb.util.Formatter;
import com.trab.devweb.service.UserService;
import com.trab.devweb.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final Formatter formatter;

    @Autowired
    public UserController(UserService userService, Formatter formatter) {
        this.userService = userService;
        this.formatter = formatter;
    }

    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return formatter.formatUser(userList);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getType() == 0 || user.getName() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Campos obrigatórios não preenchidos", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(user);
        return new ResponseEntity<>("Campos obrigatórios preenchidos", HttpStatus.ACCEPTED);
    }
}

