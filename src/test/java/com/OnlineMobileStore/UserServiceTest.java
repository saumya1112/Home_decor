package com.OnlinehomedecorStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.OnlinehomedecorStore.Repositories.UserRepo;
import com.OnlinehomedecorStore.Services.UserService;
import com.OnlinehomedecorStore.common.LoginResponse;
import com.OnlinehomedecorStore.entities.UserModel;

@SpringBootTest
public class UserServiceTest {
	@InjectMocks
	private UserService userService = new UserService();
	@Mock
	private UserRepo userRepo;

	@Test
	void testaddUser() {
		UserModel user = new UserModel();
		user.setUserID(3);
		user.setUserName("john doe");
		user.setUserPassword("12345678");
		user.setEmailId("johndoe@gmail.com");
		user.setMobileNumber(1223322339L);
		user.setUserRole("customer");
		Optional<UserModel> optionalUserModel = Optional.of(user);
		when(userRepo.findByEmailId("johndoe@gmail.com")).thenReturn(optionalUserModel);
		LoginResponse user1 = userService.addUser(user);
		assertEquals("You are already registered. Please log in.", user1.getMessage());
	}

	@Test
	void testUserById() {
		UserModel user = new UserModel();
		user.setUserID(3);
		user.setUserName("john doe");
		user.setUserPassword("12345678");
		user.setEmailId("johndoe@gmail.com");
		user.setMobileNumber(1223322339L);
		user.setUserRole("customer");

		Optional<UserModel> optionalUserModel = Optional.of(user);
		when(userRepo.findById(3)).thenReturn(optionalUserModel);
		UserModel userdetails = userService.getUserById(3);
		assertEquals("john doe", userdetails.getUserName());
	}

	// login
	@Test
	void testLogin() {
		UserModel user = new UserModel();
		user.setUserID(3);
		user.setUserName("JohnDoe");
		user.setUserPassword("12345678");
		user.setEmailId("johndoe@gmail.com");
		user.setMobileNumber(8105658277L);
		user.setUserRole("customer");
		userRepo.save(user);
		Optional<UserModel> optionalUserModel = Optional.of(user);
		when(userRepo.findByEmailId("johndoe@gmail.com")).thenReturn(optionalUserModel);
		LoginResponse user1 = userService.Login_User("johndoe@gmail.com", "12345678");
		assertEquals("Log in Successful", user1.getMessage());
	}

	// Delete
	@Test
	void testDeleteUserById() {
		UserModel user = new UserModel();
		user.setUserID(10);
		user.setUserName("john");
		user.setUserPassword("12345678");
		user.setEmailId("johndoe@gmail.com");
		user.setMobileNumber(8105658277L);
		user.setUserRole("customer");
		Optional<UserModel> optionalProduct = Optional.of(user);
		when(userRepo.findById(10)).thenReturn(optionalProduct);
		String out = userService.DeleteCustomerById(10);
		assertEquals("User " + 10 + " is Successfully Deleted", out);

	}

	// updateCustomer
	@Test
	void testUpdateCustomer() {
		UserModel user = new UserModel();
		user.setUserID(10);
		user.setUserName("john");
		user.setUserPassword("12345678");
		user.setEmailId("johndoe@gmail.com");
		user.setMobileNumber(8105658277L);
		user.setUserRole("customer");
		Optional<UserModel> optionalProduct = Optional.of(user);
		when(userRepo.findById(10)).thenReturn(optionalProduct);
		UserModel obj = userService.updateCustomer(10,
				new UserModel(10, "JohnD", "12345678", 8105658277L, "johndoe@gmail.com", "customer"));
		assertEquals("JohnD", obj.getUserName());
	}

}
