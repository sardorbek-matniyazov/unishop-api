package com.uniteam.smartshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class Input {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Product product;

    private String productName;

    private String description;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double wholesalePrice;

    @Column(nullable = false)
    private Double minimumPrice;

    @Column(nullable = false)
    private Double maximumPrice;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    public Input(
            Product product,
            String name,
            String description,
            String brand,
            Integer quantity,
            Double price,
            Double wholesalePrice,
            Double minimumPrice,
            Double maximumPrice
    ) {
        this.product = product;
        this.productName = name;
        this.description = description;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.wholesalePrice = wholesalePrice;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
    }
}
