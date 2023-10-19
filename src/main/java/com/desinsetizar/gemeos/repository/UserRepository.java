package com.desinsetizar.gemeos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.desinsetizar.gemeos.models.User;

public interface UserRepository extends CrudRepository<User, String>{
    
    @Query(value = "select * from user where email = :email and senha =:senha ", nativeQuery = true)
    public User login(String email, String senha);

    public User findById(int id);
}
