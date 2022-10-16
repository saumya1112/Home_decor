package com.OnlinehomedecorStore.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlinehomedecorStore.entities.OrderItemModel;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItemModel, Integer> {
	public List<OrderItemModel> findAllByOrderId(int Order_id);

	public void deleteByOrderId(int Order_id);
}
