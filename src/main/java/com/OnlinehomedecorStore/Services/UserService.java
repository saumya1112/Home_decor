package com.OnlinehomedecorStore.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.UserNotFoundException;
import com.OnlinehomedecorStore.Repositories.UserRepo;
import com.OnlinehomedecorStore.common.LoginResponse;
import com.OnlinehomedecorStore.entities.UserModel;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepo userRepository;

	public UserModel getUserById(int userId) throws UserNotFoundException {
		Optional<UserModel> optionalProduct = userRepository.findById(userId);
		if (!optionalProduct.isPresent())
			throw new UserNotFoundException("User id " + userId + " is Invalid");
		return optionalProduct.get();
	}

	@Override
	public LoginResponse addUser(UserModel user) {
		LoginResponse res = new LoginResponse();
		String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

		if ((user.getUserName()).isEmpty()) {
			res.setMessage("Please enter your name.");
			return res;
		}
		if ((user.getUserPassword()).isEmpty()) {
			res.setMessage("Please enter your password.");
			return res;
		}
		if (user.getUserPassword().length() < 8) {
			res.setMessage("Password not Strong.Password must be at least 8 characters long.");
			return res;
		}
		if ((user.getMobileNumber()) == null) {
			res.setMessage("Please enter your mobile number.");
			return res;
		}
		if ((user.getEmailId()).isEmpty()) {
			res.setMessage("Please enter your email.");
			return res;
		}
		Optional<UserModel> optional = userRepository.findByEmailId(user.getEmailId());
		if (!optional.isEmpty()) {
			res.setMessage("You are already registered. Please log in.");
			return res;
		}
		Pattern ptr = Pattern.compile(emailRegex);
		if (!ptr.matcher(user.getEmailId()).matches()) {
			res.setMessage("Email not valid.Please check your email");
		} else {
			res.setUser(userRepository.save(user));
			res.setMessage("Successfully registered.");
		}
		return res;
	}

	public List<UserModel> showAllCustomers() {
		List<UserModel> users = userRepository.findAll();
		List<UserModel> userModels = new ArrayList<>();
		for (UserModel user : users) {
			userModels.add(user);
		}
		return userModels;
	}

	public LoginResponse Login_User(String email, String userPassword) {
		LoginResponse res = new LoginResponse();
		Optional<UserModel> optionalEmail = userRepository.findByEmailId(email);
		if (optionalEmail.isEmpty()) {
			res.setMessage("You are not Registered with us.Please sign up");
			return res;
		}
		if (!optionalEmail.get().getUserPassword().equals(userPassword)) {
			res.setMessage("Your email or password is incorrect");
			return res;
		}
		res.setUser(optionalEmail.get());
		res.setMessage("Log in Successful");

		return res;
	}

	public String DeleteCustomerById(Integer userId) throws UserNotFoundException {
		Optional<UserModel> optional = userRepository.findById(userId);
		if (!optional.isPresent())
			throw new UserNotFoundException("User id is invalid " + userId);
		UserModel user = optional.get();
		userRepository.delete(user);
		return "User " + userId + " is Successfully Deleted";
	}

	public UserModel updateCustomer(Integer userId, UserModel user) throws UserNotFoundException {
		Optional<UserModel> optional = userRepository.findById(userId);
		if (!optional.isPresent())
			throw new UserNotFoundException("User id is invalid " + userId);
		UserModel user1 = userRepository.findById(userId).get();
		user1.setUserName(user.getUserName());
		user1.setEmailId(user.getEmailId());
		user1.setMobileNumber(user.getMobileNumber());
		user1.setUserPassword(user.getUserPassword());
		user1.setUserRole(user.getUserRole());
		userRepository.save(user1);
		return user1;
	}

}
