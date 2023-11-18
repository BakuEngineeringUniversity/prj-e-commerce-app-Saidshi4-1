package com.said.palidmarketapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List <Product> product;
}
