package com.csci334.carpark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci334.carpark.dao.IUserDao;
import com.csci334.carpark.entity.User;
@Service
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		User obj = userDao.getUserById(userId);
		return obj;
	}	
	@Override
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	@Override
	public synchronized boolean addUser(User user){
       if (userDao.userExists(user.getUsername()) || userDao.emailUsed(user.getEmail()) || userDao.mobileUsed(user.getMobile())) {
    	   return false;
       } else {
    	   userDao.addUser(user);
    	   return true;
       }
	}
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	@Override
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}
}
