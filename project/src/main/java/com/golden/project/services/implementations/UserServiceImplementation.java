package com.golden.project.services.implementations;

import com.golden.project.dao.UserRepository;
import com.golden.project.dto.UserDTO;
import com.golden.project.model.User;
import com.golden.project.services.UserServices;
import com.golden.project.utilities.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserServices {
    private Utils utils;
    private UserRepository repo;
    public UserServiceImplementation(UserRepository repo, Utils utils) {
        this.repo = repo;
        this.utils = utils;
    }
    @Override
    public List<UserDTO> getAllUsers(int page, int limit) {
        List<User> userList = new ArrayList<User>();
        if (page>0) page --;
        PageRequest pageableRequest = PageRequest.of(page, limit);
        Page<User> userPageList = repo.findAll(pageableRequest);
        userList = userPageList.getContent();
        List<UserDTO> userDtoList = new ArrayList<UserDTO>();
        for ( int i = 0; i < userList.size(); i++) {
            UserDTO userDto = new UserDTO();
            BeanUtils.copyProperties(userList.get(i), userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
    @Override
    public UserDTO getUserByUserId(String userId) {
        User user = repo.findByUserId(userId);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(user, returnValue);
        return returnValue;
    }
    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setEncryptedPassword("password-test");
        user.setEmailVerification(true);
        user.setUserId(utils.generateUserId(20));
        User createdUser = repo.save(user);
        UserDTO returnUser = new UserDTO();
        BeanUtils.copyProperties(createdUser, returnUser);
        return returnUser;
    }
    @Override
    public UserDTO updateUser(UserDTO userDto) {
        User foundUser = repo.findByUserId(userDto.getUserId());
        BeanUtils.copyProperties(userDto, foundUser);
        User updatedUser = repo.save(foundUser);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(updatedUser, returnValue);
        return returnValue;
    }
    @Override
    public void deleteUser(String userId) {
        User foundUser = repo.findByUserId(userId);
        repo.delete(foundUser);
    }
    @Override
    public UserDTO getUserByEmail(String email) {
        User foundUser = repo.findByEmail(email);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(foundUser, returnValue);
        return returnValue;
    }
}