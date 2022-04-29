package com.uniteam.smartshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class InputCash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    private Double cashAmount;
    private Double cardAmount;

    private boolean byCard;
    private boolean byCash;

    private String comment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    public InputCash(Client client, Double cashAmount, Double cardAmount, String comment) {
        this.client = client;
        this.cashAmount = cashAmount;
        this.cardAmount = cardAmount;
        this.comment = comment;
    }
}
