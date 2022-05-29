package com.uniteam.unishop.repository;

import com.uniteam.unishop.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {
    boolean existsByFullName(String fullName);

    boolean existsByFullNameAndIdNot(String fullName, Long id);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

    @Query("select c from Client c where lower(c.fullName) like %?1%")
    List<Client> findAllByFullNameLike(String fullName);
}
