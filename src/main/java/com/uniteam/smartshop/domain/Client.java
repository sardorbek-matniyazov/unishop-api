package com.uniteam.smartshop.domain;

import com.uniteam.smartshop.domain.enums.ClientType;
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
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Double balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClientType type;

    private Long inn;

    @Column(nullable = false, length = 10000)
    private String comment;

    private boolean blocked = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    public Client(
            String fullName,
            String phoneNumber,
            ClientType legal,
            Long inn,
            String comment,
            Double balance) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = legal;
        this.inn = inn;
        this.comment = comment;
        this.balance = balance;
    }

    public Client(
            String fullName,
            String phoneNumber,
            ClientType individual,
            String comment,
            double balance) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = individual;
        this.comment = comment;
        this.balance = balance;
    }

    public Client(
            UUID id,
            String fullName,
            String phoneNumber,
            ClientType legal,
            Long inn,
            String comment,
            double balance) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = legal;
        this.inn = inn;
        this.comment = comment;
        this.balance = balance;
    }

    public Client(
            UUID id,
            String fullName,
            String phoneNumber,
            ClientType individual,
            String comment,
            double balance) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.type = individual;
        this.comment = comment;
        this.balance = balance;
    }
}
