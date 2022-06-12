package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
