package com.OnlinehomedecorStore.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlinehomedecorStore.entities.OrderModel;

@Repository
public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
	public List<OrderModel> findAllByUserId(int user_Id);

}
