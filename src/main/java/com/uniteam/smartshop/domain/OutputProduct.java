package com.uniteam.smartshop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Product product;

    @JsonIgnore
    @ManyToOne(optional = false)
    private Output output;

    private Integer quantity;

    private Double cost;

    public OutputProduct(Product byId, Double cost, Integer quantity) {
        this.product = byId;
        this.cost = cost;
        this.quantity = quantity;
    }
}
