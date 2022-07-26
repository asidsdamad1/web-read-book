package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.PublishingHouseDto;
import com.example.springmvcdemo.dev.model.PublishingHouse;

import java.util.List;

public interface PublishingHouseService {
    List<PublishingHouseDto> getAll();

    PublishingHouseDto saveOrUpdater(PublishingHouseDto dto, Integer id);

    PublishingHouseDto getById(Integer id);

    boolean delete(Integer id);
}
