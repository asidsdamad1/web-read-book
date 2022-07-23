package com.example.springmvcdemo.dev.dto;

public class RoleDto extends BaseObjectDto{
    private String name;

    public RoleDto(String name) {
        super();
        this.name = name;
    }

    public RoleDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
