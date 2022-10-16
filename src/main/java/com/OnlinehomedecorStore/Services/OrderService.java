package com.OnlinehomedecorStore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.ItemNotFoundException;
import com.OnlinehomedecorStore.Exceptions.OrderNotFoundException;
import com.OnlinehomedecorStore.Repositories.OrderRepo;
import com.OnlinehomedecorStore.entities.OrderModel;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;

	// view
	public List<OrderModel> findAll() {
		return orderRepo.findAll();
	}

	// Add
	public OrderModel save(OrderModel orderModel) {
		return orderRepo.save(orderModel);
	}

	// view
	public OrderModel findById(int id) throws OrderNotFoundException {
		Optional<OrderModel> optionalProduct = orderRepo.findById(id);
		if (!optionalProduct.isPresent())
			throw new ItemNotFoundException("Order With this Id Doesn't exist" + id);
		return optionalProduct.get();
	}

	public List<OrderModel> findAllByUserId(int id) throws OrderNotFoundException {
		if (orderRepo.findAllByUserId(id).isEmpty())
			throw new ItemNotFoundException("User With this Id Doesn't exist" + id);
		return orderRepo.findAllByUserId(id);
	}

}
