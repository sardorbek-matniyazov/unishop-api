package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    boolean existsByFullName(String fullName);

    boolean existsByFullNameAndIdNot(String fullName, Long id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
