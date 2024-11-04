package com.teja.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teja.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity //maps our java class with database table
@Data // for getter setter methods
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//TO GENERATE ID AUTOMATICALLY
    private Long id;
    private String fullName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;
    @JsonIgnore //when i fetch user i don't need the list of order
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // User will have 1 to many relations with order mapped by customer don't create seperate table by mapping u can use customer table
    private List<Order> orders = new ArrayList<>();
    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // one user many addresses // when user deleted all the address will be deleted so used cascadetype.all
    private List<Address> addresses = new ArrayList<>();

}
