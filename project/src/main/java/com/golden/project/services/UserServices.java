package com.golden.project.services;
import java.util.List;
import com.golden.project.model.User;

//creating interface for all of the methods we are going to be using in the user controller

public interface UserServices {
    List<User> getAllUsers();

    User getUser(Long id);

    void addUser(User user);
}
