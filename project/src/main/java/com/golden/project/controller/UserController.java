package com.golden.project.controller;

import com.golden.project.dto.UserDTO;
import com.golden.project.model.User;
import com.golden.project.model.request.UserRequest;
import com.golden.project.model.response.UserResponse;
import com.golden.project.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserServices userServices; //taking in user services

    public UserController(UserServices userServices) {
    this.userServices = userServices;
}

    @GetMapping
    public List<UserResponse> getAllUsers(
        @RequestParam(value = "page", defaultValue = "1") int page,
                @RequestParam(value = "limit", defaultValue = "5") int limit) {

        List<UserDTO> userDTOList = userServices.getAllUsers(page, limit);
        List<UserResponse> userResponseList = new ArrayList<UserResponse>();
        for(int i = 0; i < userDTOList.size(); i++) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(userDTOList.get(i), userResponse);
            userResponseList.add(userResponse);
        }
        return userResponseList;

    }

    @GetMapping(path="/{userId}")
    public UserResponse getUserByUserId(@PathVariable String userId) {
        UserDTO userDTO = userServices.getUserByUserId(userId); //Reminder" We called the class UserDTO and named the class instance userDTO
        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(userDTO, returnValue); //taking all the properties it gets from userDTO and copies them to the return value user response
        return returnValue;

    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);
        UserDTO createdUser = userServices.createUser(userDTO);
        UserResponse returnUser = new UserResponse();
        BeanUtils.copyProperties(createdUser, returnUser);
        return returnUser;

    }


    @PutMapping //This is where we update a user in the database
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable String userId) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);
        userDTO.setUserId(userId);
        UserDTO updatedUserDto = userServices.updateUser(userDTO);
        UserResponse returnValue = new UserResponse();
        BeanUtils.copyProperties(updatedUserDto, returnValue);
        return returnValue;
    }

    @DeleteMapping(path="/{userId}") //This is where we delete a user in the database
    public void deleteUser(@PathVariable String userId) {
        userServices.deleteUser(userId);
    }


    @GetMapping(path="/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
       UserDTO userDTO = userServices.getUserByEmail(email);
       UserResponse returnValue = new UserResponse();
       BeanUtils.copyProperties(userDTO, returnValue);
        return returnValue;
    }


}
