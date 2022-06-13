package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<TagDto> findBySerialNumber(Long serialNumber);
    List<TagDto> findAllByProjectSerialNumber(Long projectSerialNumber);
}
