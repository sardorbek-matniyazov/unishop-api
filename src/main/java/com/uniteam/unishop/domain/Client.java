package com.uniteam.unishop.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Client full name is required")
    @Size(min = 4, max = 50, message = "name must be in range of [4:50]")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Client phone number is required")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "comment is required")
    @Column(nullable = false, length = 10000)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
