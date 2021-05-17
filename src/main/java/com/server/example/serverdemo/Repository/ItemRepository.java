package com.server.example.serverdemo.Repository;

import com.server.example.serverdemo.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    /*@Query("SELECT item FROM Item i WHERE i.status = :status")
    List<Item> findItemByStatus(@Param("status") Item.Status status);*/

}
