package com.uniteam.smartshop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    private Product product;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @ManyToOne(optional = false)
    private Output output;

    private Integer quantity;

    @Column(nullable = false)
    private Double cost;

    private Double profitCost;


    public OutputProduct(
            Product realProduct,
            Double cost,
            Integer quantity,
            double profitCost,
            Output output) {
        this.product = realProduct;
        this.cost = cost;
        this.quantity = quantity;
        this.profitCost = profitCost;
        this.output = output;
    }
}
