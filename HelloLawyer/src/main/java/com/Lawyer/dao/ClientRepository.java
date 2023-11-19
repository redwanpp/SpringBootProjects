package com.Lawyer.dao;

import com.Lawyer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("select c from Client c where c.email = :email")
	public Client getUserByUserName(@Param("email") String email);
}
