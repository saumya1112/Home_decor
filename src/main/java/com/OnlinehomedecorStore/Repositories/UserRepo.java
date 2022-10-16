package com.OnlinehomedecorStore.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlinehomedecorStore.entities.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {

	public Optional<UserModel> findByEmailId(String email);

	// public Optional<UserModel> findByEmailIdAndUserPassword(String email, String
	// userPassword);
}
