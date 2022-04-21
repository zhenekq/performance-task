package com.example.perfomance_project.service;

import com.example.perfomance_project.domain.Page;
import com.example.perfomance_project.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getUsers(Page page);

    User getById(Long id);

    User create(User user);

    User updateById(Long id, User user);

    User deleteById(Long id);

}
