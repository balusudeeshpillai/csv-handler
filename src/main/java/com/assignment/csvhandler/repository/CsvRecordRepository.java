package com.assignment.csvhandler.repository;

import com.assignment.csvhandler.entity.CsvRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CsvRecordRepository extends JpaRepository<CsvRecordEntity, Long> {

    Optional<CsvRecordEntity> findByCode(String code);

}
