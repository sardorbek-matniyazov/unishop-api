package com.uniteam.smartshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double wholesalePercent;

    @Column(nullable = false)
    private Double minimumPercent;

    @Column(nullable = false)
    private Double maximumPercent;

    @Column(nullable = false)
    private Double minimumAmount;
}
