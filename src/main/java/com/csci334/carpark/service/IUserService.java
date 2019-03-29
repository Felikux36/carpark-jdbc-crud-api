package com.csci334.carpark.service;

import java.util.List;

import com.csci334.carpark.entity.User;

public interface IUserService {
	List<User> getAllUsers();
    User getUserById(int userId);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
}
