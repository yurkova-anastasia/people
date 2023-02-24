package com.ku.people.repository;

import com.ku.people.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("FROM Authority a LEFT JOIN FETCH a.roles WHERE a.id = :id")
    Optional<Authority> findById(Long id);
}