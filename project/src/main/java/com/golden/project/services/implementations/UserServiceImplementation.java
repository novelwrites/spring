package com.golden.project.services.implementations;

import com.golden.project.dao.UserRepository;
import com.golden.project.model.User;
import com.golden.project.services.UserServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserServices { //makes all the actual calls from the repo
    private UserRepository userRepository;


    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() { //gets all users
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        return users;
    }

    @Override
    public User getUser(Long id) { //takes in an id with a data type of long
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public void addUser(User user) { //create a user
        userRepository.save(user);

    }
}
