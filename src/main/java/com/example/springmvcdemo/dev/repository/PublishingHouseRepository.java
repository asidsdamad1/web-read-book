package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Integer> {
    @Query("select count(e) from PublishingHouse e")
    int countPublishingHouses();
}
