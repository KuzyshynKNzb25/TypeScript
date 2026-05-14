package com.bit_project.typeweatherwithoutgit.repository;

import com.bit_project.typeweatherwithoutgit.entity.WithoutResults;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WithoutResultsRepository extends JpaRepository<WithoutResults, Long> {
    Optional<WithoutResults> findFirstByUserType(String name);
}
