package com.OnlineMobileStore.service;

import com.OnlineMobileStore.Exceptions.UserAllreadyException;
import com.OnlineMobileStore.Repositories.UserRepo;
import com.OnlineMobileStore.Services.UserService;
import com.OnlineMobileStore.common.LoginResponse;
import com.OnlineMobileStore.entities.UserModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserService userService=new UserService();
    @Mock
    private UserRepo userRepo;
    @Test
    void testaddUser() {
        UserModel user = new UserModel();
        user.setUserID(3);
        user.setUserName("swathi shetty");
        user.setUserPassword("123456");
        user.setEmailId("swathi@gmail.com");
        user.setMobileNumber(1223322339L);
        user.setUserRole("customer");
        Optional<UserModel> optionalUserModel = Optional.of(user);
        when(userRepo.findByEmailId("swathi@gmail.com")).thenReturn(optionalUserModel);
        LoginResponse user1 = userService.addUser(user);
        assertEquals("You are already registered. Please log in.", user1.getMessage());
    }

    @Test
    void testUserById(){
        UserModel user = new UserModel();
        user.setUserID(3);
        user.setUserName("swathi shetty");
        user.setUserPassword("123456");
        user.setEmailId("swathi@gmail.com");
        user.setMobileNumber(1223322339L);
        user.setUserRole("customer");

        Optional<UserModel> optionalUserModel = Optional.of(user);
        when(userRepo.findById(3)).thenReturn(optionalUserModel);
        UserModel userdetails = userService.getUserById(3);
        assertEquals("swathi shetty", userdetails.getUserName());
    }
    //login
    @Test
    void testLogin() {
        UserModel user = new UserModel();
        user.setUserID(3);
        user.setUserName("PrashantA");
        user.setUserPassword("1234");
        user.setEmailId("prashant@gmail.com");
        user.setMobileNumber(8105658277L);
        user.setUserRole("customer");
        userRepo.save(user);
        Optional<UserModel> optionalUserModel = Optional.of(user);
        when(userRepo.findByEmailId("prashant@gmail.com")).thenReturn(optionalUserModel);
        LoginResponse user1 = userService.Login_User("prashant@gmail.com","1234");
        assertEquals("Log in Successful", user1.getMessage());
    }



    //Delete
    @Test
    void testDeleteUserById() {
        UserModel user = new UserModel();
        user.setUserID(10);
        user.setUserName("prashant");
        user.setUserPassword("123456");
        user.setEmailId("prashant@gmail.com");
        user.setMobileNumber(8105658277L);
        user.setUserRole("customer");
        Optional<UserModel> optionalProduct = Optional.of(user);
        when(userRepo.findById(10)).thenReturn(optionalProduct);
        String out=userService.DeleteCustomerById(10);
        assertEquals("User "+10+" is Successfully Deleted", out);

    }
    //updateCustomer
    @Test
    void testUpdateCustomer(){
        UserModel user = new UserModel();
        user.setUserID(10);
        user.setUserName("prashant");
        user.setUserPassword("123456");
        user.setEmailId("prashant@gmail.com");
        user.setMobileNumber(8105658277L);
        user.setUserRole("customer");
        Optional<UserModel> optionalProduct = Optional.of(user);
        when(userRepo.findById(10)).thenReturn(optionalProduct);
        UserModel obj=userService.updateCustomer(10, new UserModel(10,"PrashantA","1234",8105658277L,"aprashant19@gmail.com","customer"));
        assertEquals("PrashantA",obj.getUserName());
    }



    }
