package com.example.springmvcdemo.dev.service;

import com.example.springmvcdemo.dev.dto.AnalystDto;
import org.springframework.stereotype.Service;

@Service
public interface AnalystService {
    AnalystDto getAnalyst();
}
