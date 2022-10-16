package com.OnlinehomedecorStore.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.common.LoginResponse;
import com.OnlinehomedecorStore.entities.UserModel;

@Service
public interface IUserService {

	public LoginResponse addUser(UserModel user);

	public List<UserModel> showAllCustomers();

	public LoginResponse Login_User(String userName, String userPassword);

	public UserModel getUserById(int userId) throws ItemNotFoundException;
}