package com.OnlinehomedecorStore.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlinehomedecorStore.entities.CartModel;

@Repository
public interface CartRepo extends JpaRepository<CartModel, Integer> {
	public List<CartModel> findAllByUserid(int User_id);

	public Boolean existsByUseridAndProduct(int User_id, int product);

	@Query("SELECT SUM(c.price) FROM CartModel c WHERE c.userid = :user_id")
	public float TotalCost(int user_id);
}
