package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    Optional<MilestoneDto> findBySerialNumber(Long serialNumber);
    List<MilestoneDto> findAllByProjectSerialNumber(Long projectSerialNumber);
}
