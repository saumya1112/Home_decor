package com.OnlinehomedecorStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OnlinehomedecorStore.entities.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
}
