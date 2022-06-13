package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> getComments(Long taskSerialNumber);

    Comment createComment(CommentCreateRequest request);

    Comment modifyComment(CommentModifyRequest request);

    void deleteComment(Long serialNumber);

    CommentDto getComment(Long serialNumber);
}
