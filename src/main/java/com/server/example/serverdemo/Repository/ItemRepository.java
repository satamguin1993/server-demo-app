package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /*@Query("SELECT item FROM Item i WHERE i.status = :status")
    List<Item> findItemByStatus(@Param("status") Item.Status status);*/

}
