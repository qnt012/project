package com.nhnacademy.project.service;

import com.nhnacademy.project.entity.Milestone;
import java.util.List;

public interface MilestoneService {
    List<Milestone> getMilestones(Long projectSerialNumber);
}
