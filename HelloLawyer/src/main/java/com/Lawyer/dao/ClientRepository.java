package com.Lawyer.dao;

import com.Lawyer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findByEmail(String email);
}
