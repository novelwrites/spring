package com.golden.project.services;
import java.util.List;

import com.golden.project.dto.UserDTO;
import com.golden.project.model.User;

//creating interface for all of the methods we are going to be using in the user controller

public interface UserServices {

    List<UserDTO> getAllUsers(int page, int limit);

    UserDTO getUserByUserId(String userId);

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(String userId);
}
