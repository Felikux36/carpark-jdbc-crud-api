package com.csci334.carpark.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet row, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(row.getInt("user_id"));
		user.setUsername(row.getString("username"));
		user.setFirstname(row.getString("firstname"));
		user.setLastname(row.getString("lastname"));
		user.setEmail(row.getString("email"));
		user.setMobile(row.getString("mobile"));
		return user;
	}
	
}