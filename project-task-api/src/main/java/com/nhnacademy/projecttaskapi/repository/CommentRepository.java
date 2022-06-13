package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<CommentDto> findBySerialNumber(Long serialNumber);
    List<CommentDto> findAllByTaskSerialNumber(Long taskSerialNumber);
}
