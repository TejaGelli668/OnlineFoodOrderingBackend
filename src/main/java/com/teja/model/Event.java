package com.teja.model;

import jakarta.persistence.*;

import java.util.List;

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1000)
    @ElementCollection// creates seperate table for images
    private List<String> image;
    private String name;
    private String restaurant;
    private String location;
    private String startedAt;
    private String endsAt;
}
