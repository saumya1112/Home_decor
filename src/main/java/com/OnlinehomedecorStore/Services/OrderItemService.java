package com.OnlinehomedecorStore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlinehomedecorStore.Exceptions.OrderItemNotFoundException;
import com.OnlinehomedecorStore.Repositories.OrderItemRepo;
import com.OnlinehomedecorStore.entities.OrderItemModel;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepo orderItemRepo;

	public List<OrderItemModel> findAll() {
		return orderItemRepo.findAll();
	}

	public void save(OrderItemModel orderItemModel) {
		orderItemRepo.save(orderItemModel);
	}

	public Optional<OrderItemModel> findById(int id) throws OrderItemNotFoundException {
		if (orderItemRepo.findById(id).isEmpty())
			throw new OrderItemNotFoundException("Order Item With this Id Doesn't exist" + id);
		return orderItemRepo.findById(id);
	}

	public List<OrderItemModel> findAllByOrderid(int id) throws OrderItemNotFoundException {
		if (orderItemRepo.findAllByOrderId(id).isEmpty())
			throw new OrderItemNotFoundException("Order Item With this Id Doesn't exist" + id);
		return orderItemRepo.findAllByOrderId(id);
	}

}
