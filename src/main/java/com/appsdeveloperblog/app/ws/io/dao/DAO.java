package com.appsdeveloperblog.app.ws.io.dao;

import java.util.List;

import com.appsdeveloperblog.app.ws.shared.dto.UserDTO;

public interface DAO {
	void openConnection();
	UserDTO getUserByUserName(String UserName);
	UserDTO getUserByEmailToken(String token);
	UserDTO saveUser(UserDTO user);
	UserDTO getUser(String id);
	List<UserDTO> getUsers(int start, int limit);
	void updateUser(UserDTO userProfile);
	void deleteUser(UserDTO userProfile);
	void closeConnection();
	

}
