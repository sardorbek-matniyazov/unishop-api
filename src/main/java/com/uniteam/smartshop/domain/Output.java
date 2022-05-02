package com.uniteam.smartshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @OneToOne(mappedBy = "output", cascade = CascadeType.ALL)
    private Debt debt;

    private Double costCash;
    private Double costCard;
    private Date expiredDate;

    private boolean byCard;
    private boolean byCash;
    private boolean byDebt;

    private String comment;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private UUID createdBy;
}
