package com.csci334.carpark.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csci334.carpark.entity.User;
import com.csci334.carpark.entity.UserRowMapper;
@Transactional
@Repository
public class UserDao implements IUserDao {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public User getUserById(int userId) {
		String sql = "SELECT user_id, username, firstname, lastname, email FROM users WHERE user_Id = ?";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
		User user = jdbcTemplate.queryForObject(sql, rowMapper, userId);
		return user;
	}
	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT user_id, username, firstname, lastname, email, mobile FROM users";
        RowMapper<User> rowMapper = new UserRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}	
	@Override
	public void addUser(User user) {
		//Add user
		String sql = "INSERT INTO users (user_id, username, password, role, enabled, firstname, lastname, email, mobile) values (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), user.getEnabled(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getMobile());
		
		//Fetch user id
		sql = "SELECT user_id FROM users WHERE username = ? and email=?";
		int userId = jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(), user.getEmail());
		
		//Set user id 
		user.setUserId(userId);
	}
	@Override
	public void updateUser(User user) {
		String sql = "UPDATE users SET password=?, firstname=?, lastname=?, email=?, mobile=? WHERE user_id=?";
		jdbcTemplate.update(sql, user.getPassword(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getMobile(), user.getUserId());
	}
	@Override
	public void deleteUser(int userId) {
		String sql = "DELETE FROM users WHERE userId=?";
		jdbcTemplate.update(sql, userId);
	}
	@Override
	public boolean userExists(String username) {
		String sql = "SELECT count(*) FROM users WHERE username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
		if(count == 0) {
    		return false;
		} else {
			return true;
		}
	}
	@Override
	public boolean emailUsed(String email) {
		String sql = "SELECT count(*) FROM users WHERE email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, email);
		if(count == 0) {
    		return false;
		} else {
			return true;
		}
	}
	@Override
	public boolean mobileUsed(String mobile) {
		String sql = "SELECT count(*) FROM users WHERE mobile = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, mobile);
		if(count == 0) {
    		return false;
		} else {
			return true;
		}
	}
}
