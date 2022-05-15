package com.uniteam.unishop.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(optional = false)
    private Category category;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
