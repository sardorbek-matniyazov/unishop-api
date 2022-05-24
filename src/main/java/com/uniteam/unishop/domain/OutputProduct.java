package com.uniteam.unishop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class OutputProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    private Product product;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OutputProduct that = (OutputProduct) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
