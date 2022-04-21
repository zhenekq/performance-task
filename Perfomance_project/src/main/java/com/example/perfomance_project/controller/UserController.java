package com.example.perfomance_project.controller;

import com.example.perfomance_project.domain.Page;
import com.example.perfomance_project.domain.User;
import com.example.perfomance_project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll(Page page){
        List<User> users = service.getUsers(page);
        return users;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id){
        return service.getById(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @PutMapping("{id}")
    public User updateById(@PathVariable Long id, @RequestBody User user){
        return service.updateById(id, user);
    }

    @DeleteMapping("{id}")
    public User deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }

}
