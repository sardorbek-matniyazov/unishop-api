package com.uniteam.unishop.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Input {
    @Id
    @GeneratedValue
    private Long id;

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    private Timestamp updatedDate;

    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    public Input(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Input input = (Input) o;
        return id != null && Objects.equals(id, input.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
