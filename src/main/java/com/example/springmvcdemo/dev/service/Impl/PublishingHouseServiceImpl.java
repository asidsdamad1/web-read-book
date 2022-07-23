package com.example.springmvcdemo.dev.service.Impl;

import com.example.springmvcdemo.dev.dto.AuthorDto;
import com.example.springmvcdemo.dev.dto.PublishingHouseDto;
import com.example.springmvcdemo.dev.model.Author;
import com.example.springmvcdemo.dev.model.PublishingHouse;
import com.example.springmvcdemo.dev.repository.PublishingHouseRepository;
import com.example.springmvcdemo.dev.service.PublishingHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishingHouseServiceImpl implements PublishingHouseService {
    
    @Autowired
    private PublishingHouseRepository publishingRepository;
    
    @Override
    public List<PublishingHouseDto> getAll() {
        List<PublishingHouse> publishingHouses = publishingRepository.findAll();
        List<PublishingHouseDto> dtos = new ArrayList<>();
        for (PublishingHouse publishingHouse : publishingHouses) {
            dtos.add(new PublishingHouseDto(publishingHouse, true));
        }
        return dtos;
    }

    @Override
    public PublishingHouseDto saveOrUpdater(PublishingHouseDto dto, Long id) {
        if(dto != null) {
            PublishingHouse entity = null;
            if(id != null)
                entity = publishingRepository.findById(id).orElse(null);
            if(entity == null)
                entity = new PublishingHouse();

            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setAddress(dto.getAddress());
            entity.setDescription(dto.getDescription());

            entity = publishingRepository.save(entity);
            return new PublishingHouseDto(entity, true);
        }
        return null;
    }

    @Override
    public PublishingHouseDto getById(Long id) {
        if(id != null) {
            PublishingHouse entity = publishingRepository.getById(id);
            return new PublishingHouseDto(entity, true);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(id != null) {
            publishingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
