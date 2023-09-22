package com.company.demo.repository;

import com.company.demo.entity.Order;
import com.company.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    @Query(value = "select * from obo.users where id != 1", nativeQuery = true)
    List<User> findAllNotAdmin();

    @Query(nativeQuery = true, value = "Select * from obo.orders where buyer = ?1")
    List<Object> checkProductInOrder(Long id);
}
