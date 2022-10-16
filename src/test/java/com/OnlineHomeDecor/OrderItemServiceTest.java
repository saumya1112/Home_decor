package com.OnlinehomedecorStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.OnlinehomedecorStore.Exceptions.OrderNotFoundException;
import com.OnlinehomedecorStore.Repositories.OrderItemRepo;
import com.OnlinehomedecorStore.Services.OrderItemService;
import com.OnlinehomedecorStore.entities.OrderItemModel;

@SpringBootTest
public class OrderItemServiceTest {
	@InjectMocks
	private OrderItemService orderItemService = new OrderItemService();
	@Mock
	OrderItemRepo orderItemRepo;
	//
	private int Id;

	private int orderId;

	private int mobileId;
	private int quantity;
	private float totalCost;

	// save
	@Test
	void testAddOrder() {
		OrderItemModel orderItemModel = new OrderItemModel();
		orderItemModel.setOrderId(1);
		orderItemModel.setId(1);
		orderItemModel.setItemId(1);
		orderItemModel.setQuantity(2);
		orderItemModel.setTotalCost(40000);
		when(orderItemRepo.save(orderItemModel)).thenReturn(orderItemModel);
		orderItemService.save(orderItemModel);
		verify(orderItemRepo, times(1)).save(orderItemModel);
	}

	// findById
	@Test
	void testOrderViewById() throws OrderNotFoundException {
		OrderItemModel orderItemModel = new OrderItemModel();
		orderItemModel.setOrderId(1);
		orderItemModel.setId(1);
		orderItemModel.setItemId(1);
		orderItemModel.setQuantity(2);
		orderItemModel.setTotalCost(40000);
		Optional<OrderItemModel> optionalProduct = Optional.of(orderItemModel);
		when(orderItemRepo.findById(100)).thenReturn(optionalProduct);
		Optional<OrderItemModel> order = orderItemService.findById(100);
		assertEquals(40000, order.get().getTotalCost());
	}

	// findAllByUserId
	@Test
	void testOrderViewAllByUserId() throws OrderNotFoundException {
		OrderItemModel orderItemModel = new OrderItemModel();
		orderItemModel.setOrderId(3);
		orderItemModel.setId(1);
		orderItemModel.setItemId(1);
		orderItemModel.setQuantity(2);
		orderItemModel.setTotalCost(40000);
		OrderItemModel orderItemModel2 = new OrderItemModel();
		orderItemModel2.setOrderId(3);
		orderItemModel2.setId(2);
		orderItemModel2.setItemId(3);
		orderItemModel2.setQuantity(1);
		orderItemModel2.setTotalCost(12000);
		List<OrderItemModel> orderItemList = new ArrayList<>();
		orderItemList.add(orderItemModel);
		orderItemList.add(orderItemModel2);
		when(orderItemRepo.findAllByOrderId(3)).thenReturn(orderItemList);
		List<OrderItemModel> products = orderItemService.findAllByOrderid(3);
		assertEquals(2, products.size());
	}

}
