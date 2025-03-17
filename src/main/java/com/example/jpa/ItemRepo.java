package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Item I WHERE I.id = ?1")
    void deleteItemById(int id);
}
