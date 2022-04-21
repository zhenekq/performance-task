package com.example.perfomance_project.service.impl;

import com.example.perfomance_project.domain.Page;
import com.example.perfomance_project.domain.User;
import com.example.perfomance_project.repository.UserRepository;
import com.example.perfomance_project.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsers(Page page) {
        if(page.getPage() < 0 || page.getSize() < 0){
            throw new IllegalArgumentException("Pages cannot be below zero!!!");
        }
        Pageable pageable = PageRequest.of(page.getPage() - 1, page.getSize());
        return repository.findAll(pageable).toList();
    }

    @Override
    public User getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found!"));
    }

    @Override
    public User create(User user) {
        if(user.getUsername() == null || user.getPassword() == null){
            throw new IllegalStateException("Data cannot be nullable!!!");
        }
        return repository.save(user);
    }

    @Override
    public User updateById(Long id, User user) {
        if(user.getUsername() == null || user.getPassword() == null){
            throw new IllegalStateException("Data cannot be nullable!!!");
        }
        User currentUser = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found!"));
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        return repository.save(currentUser);
    }

    @Override
    public User deleteById(Long id) {
        User currentUser = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found!"));
        repository.delete(currentUser);
        return currentUser;
    }
}
