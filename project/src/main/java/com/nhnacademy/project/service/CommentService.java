package com.nhnacademy.project.service;

import com.nhnacademy.project.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(Long taskSerialNumber, String memberId, Long projectSerialNumber, String content);

    List<CommentDto> getComments(Long taskSerialNumber);

    CommentDto getComment(Long serialNumber);

    void modifyComment(Long serialNumber, String content);

    void deleteComment(Long serialNumber);
}
