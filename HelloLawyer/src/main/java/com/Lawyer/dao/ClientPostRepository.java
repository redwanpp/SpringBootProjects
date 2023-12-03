package com.Lawyer.dao;

import com.Lawyer.entities.ClientPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientPostRepository extends JpaRepository<ClientPost, Integer> {

    @Query("from ClientPost as cp where cp.client.id =:clientId")
    public List<ClientPost> findClientPostByClient(@Param("clientId") int clientId);

}
