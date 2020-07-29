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
    public User getUserById(Long id) { //takes in an id with a data type of long
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public void addUser(User user) { //create a user
        userRepository.save(user);

    }

    @Override
    public User getUserByEmail(String email) {
        User returnUser = userRepository.findByEmail(email);
        return returnUser;
    }

    @Override
    public void updateUser(User user) {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll(); //creating an array list object of users
        for(int i = 0;i < users.size(); i++) {
            if(users.get(i).getId() == user.getId()) {
                userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteUser(Long id) { //delete a user
       userRepository.deleteById(id);
    }
}
