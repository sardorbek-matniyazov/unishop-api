package com.uniteam.smartshop.repository;

import com.uniteam.smartshop.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, UUID> {
    boolean existsByFullName(String fullName);

    boolean existsByFullNameAndIdNot(String fullName, UUID id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, UUID id);
}
