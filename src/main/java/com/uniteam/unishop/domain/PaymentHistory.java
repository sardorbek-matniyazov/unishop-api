package com.uniteam.unishop.domain;

import com.uniteam.unishop.domain.enums.PaymentType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
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
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    private Output output;

    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    public PaymentHistory(Double costCard, PaymentType card, Output output) {
        this.amount = costCard;
        type = card;
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentHistory that = (PaymentHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
