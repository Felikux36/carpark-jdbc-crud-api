package com.csci334.carpark.dao;

import java.util.List;

import com.csci334.carpark.entity.User;

public interface IUserDao {
	List<User> getAllUsers();
    User getUserById(int userId);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    boolean userExists(String username);
    boolean emailUsed(String email);
    boolean mobileUsed(String mobile);
}
