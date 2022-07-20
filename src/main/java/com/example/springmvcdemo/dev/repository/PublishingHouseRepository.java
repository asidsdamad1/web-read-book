package com.example.springmvcdemo.dev.repository;

import com.example.springmvcdemo.dev.model.PublishingHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {
}
