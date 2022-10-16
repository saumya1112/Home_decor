package com.OnlineMobileStore.service;

import com.OnlineMobileStore.Exceptions.OrderNotFoundException;
import com.OnlineMobileStore.Repositories.OrderRepo;
import com.OnlineMobileStore.Services.OrderService;
import com.OnlineMobileStore.entities.MobileModel;
import com.OnlineMobileStore.entities.OrderModel;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService=new OrderService();
    @Mock
    OrderRepo orderRepo;

    @Test
    void testAddOrder(){
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderID(10);
        orderModel.setStatus("Confirmed");
        orderModel.setTotalCost(100500);
        orderModel.setUserId(4);
        when(orderRepo.save(orderModel)).thenReturn(orderModel);
        OrderModel newProduct = orderService.save(orderModel);
        assertEquals("Confirmed",newProduct.getStatus());
        verify(orderRepo,times(1)).save(orderModel);
    }

    @Test
    void testViewAllOrders(){
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderID(10);
        orderModel.setStatus("Confirmed");
        orderModel.setTotalCost(100500);
        orderModel.setUserId(4);
        OrderModel orderModel1=new OrderModel();
        orderModel.setOrderID(11);
        orderModel.setStatus("Dispatched");
        orderModel.setTotalCost(100000);
        orderModel.setUserId(3);
        OrderModel orderModel2=new OrderModel();
        orderModel.setOrderID(12);
        orderModel.setStatus("Confirmed");
        orderModel.setTotalCost(100200);
        orderModel.setUserId(2);
        List<OrderModel> orderList = new ArrayList<>();
        orderList.add(orderModel);
        orderList.add(orderModel1);
        orderList.add(orderModel2);
        when(orderRepo.findAll()).thenReturn(orderList);
        List<OrderModel> products = orderService.findAll();
        assertEquals(3,products.size());

    }
    @Test
    void testOrderViewById() throws OrderNotFoundException {
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderID(10);
        orderModel.setStatus("Confirmed");
        orderModel.setTotalCost(100500);
        orderModel.setUserId(4);
        Optional<OrderModel> optionalProduct = Optional.of(orderModel);
        when(orderRepo.findById(100)).thenReturn(optionalProduct);
        OrderModel order = orderService.findById(100);
        assertEquals("Confirmed", order.getStatus());
    }
    @Test
    void testOrderViewAllByUserId() throws OrderNotFoundException {
        OrderModel orderModel=new OrderModel();
        orderModel.setOrderID(10);
        orderModel.setStatus("Confirmed");
        orderModel.setTotalCost(100500);
        orderModel.setUserId(3);
        OrderModel orderModel1=new OrderModel();
        orderModel.setOrderID(11);
        orderModel.setStatus("Dispatched");
        orderModel.setTotalCost(100000);
        orderModel.setUserId(3);
        List<OrderModel> orderList = new ArrayList<>();
        orderList.add(orderModel);
        orderList.add(orderModel1);
        when(orderRepo.findAllByUserId(3)).thenReturn(orderList);
        List<OrderModel> products = orderService.findAllByUserId(3);
        assertEquals(2,products.size());
    }
}
