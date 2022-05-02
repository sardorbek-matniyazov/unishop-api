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
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    private Double amount;

    private Date expiredDate;

    @JsonIgnore
    @OneToOne(optional = false)
    private Output output;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    public Debt(Client client, Double costDebt, Date expiredDate, Output output) {
        this.client = client;
        this.amount = costDebt;
        this.expiredDate = expiredDate;
        this.output = output;
    }
}
