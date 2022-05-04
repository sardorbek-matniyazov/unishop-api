package com.uniteam.smartshop.domain;

import com.uniteam.smartshop.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @OneToMany(mappedBy = "output", cascade = CascadeType.ALL)
    private Set<OutputProduct> products;

    @OneToMany(mappedBy = "output", cascade = CascadeType.REMOVE)
    private Set<PaymentHistory> paymentHistories;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

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
