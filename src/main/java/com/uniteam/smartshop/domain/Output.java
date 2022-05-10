package com.uniteam.smartshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uniteam.smartshop.domain.enums.PaymentStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Output implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @ManyToOne(optional = false)
    private Client client;

    @OneToMany(mappedBy = "output", cascade = CascadeType.PERSIST)
    private Set<OutputProduct> products;

    @JsonIgnore
    @OneToMany(mappedBy = "output", cascade = CascadeType.REMOVE)
    private Set<PaymentHistory> paymentHistories;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    private double debtAmount;

    private Date expiredDate;

    private String comment;

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
}
