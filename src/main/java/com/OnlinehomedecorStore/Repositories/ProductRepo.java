package com.OnlinehomedecorStore.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlinehomedecorStore.entities.ItemModel;

@Repository
public interface ProductRepo extends JpaRepository<ItemModel, Integer> {
	@Query(value = "SELECT * FROM item_model p WHERE p.item_name = :itemname", nativeQuery = true)
	public List<ItemModel> findByItemName(String itemname);
}
