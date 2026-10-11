package com.hospital.repository;

import com.hospital.model.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
