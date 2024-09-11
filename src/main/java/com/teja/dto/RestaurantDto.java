package com.teja.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto {
    private String title;
    @Column(length = 1000) // because of bigstream of image url
    private List<String> images;
    private String description;
    private Long id;
}
