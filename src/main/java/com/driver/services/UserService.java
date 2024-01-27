package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = new User(username,password);
        User newUserAdded = userRepository3.save(user);
        return newUserAdded;
    }

    public void deleteUser(int userId){
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password){
        Optional<User> userList = userRepository3.findById(id);
//        if(userList.isEmpty()){
//            throw new Exception("User with given id not found");
//        }
        User user = userList.get();
        user.setPassword(password);
        User newUserUpdated = userRepository3.save(user);
        return newUserUpdated;
    }
}
