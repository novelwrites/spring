package com.golden.project.controller;

import com.golden.project.model.User;
import com.golden.project.services.UserServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserServices userServices; //taking in user services

    public UserController(UserServices userServices) {
    this.userServices = userServices;
}

    @GetMapping
    public List<User> getAllUsers() { //calling user services interface getallusers and saving that to a list
        List<User> users = userServices.getAllUsers();
        return users;

    }

    @GetMapping(path="/{id}") //id is how we get single user from database
    public User getSingleUser(@PathVariable Long id) { //path varies according to the id
        User user = userServices.getUser(id); //gets all of the information for that id - userServices is the
        //interface and it is calling the method get User and bringing in the information from the database attached
        //to the id.
        return user;
    }

    @PostMapping
    public void createUser(@RequestBody User user) { //This is where we create a user for the database based on the body
        //of the post request
        userServices.addUser(user);
    }

    //My new code starts here
    //@PutMapping(path="/{id}") //This is where we update a user in the database
    //public void updateUser(@PathVariable Long id) {
        //userServices.updateUser(updateUser(id));
    //}

    @DeleteMapping(path="/{id}") //This is where we delete a user in the database
    public void deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
    }
}
