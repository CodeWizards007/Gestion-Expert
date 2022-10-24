package com.example.expert.repository;

import com.example.expert.entity.Expert;
import com.example.expert.entity.Rapport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RapportRepository extends JpaRepository<Rapport, Long> {


}
