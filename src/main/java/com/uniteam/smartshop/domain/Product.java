package com.uniteam.smartshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double minimumPrice;

    @Column(nullable = false)
    private Double maximumPrice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Category category;

    private boolean active = true;

    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    private Date updatedDate;

    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    public Product(
            String name,
            String description,
            String brand,
            Integer quantity,
            Double price,
            Double minimumPrice,
            Double maximumPrice) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;

    }
}
