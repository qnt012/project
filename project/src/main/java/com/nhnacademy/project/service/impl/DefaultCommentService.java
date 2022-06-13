package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.CommentDto;
import com.nhnacademy.project.domain.request.project.CommentCreateRequest;
import com.nhnacademy.project.domain.request.project.CommentModifyRequest;
import com.nhnacademy.project.repository.ProjectRepository;
import com.nhnacademy.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {
    private final ProjectRepository projectRepository;

    @Override
    public void createComment(Long taskSerialNumber, String memberId, Long projectSerialNumber, String content) {
        CommentCreateRequest requestBody = new CommentCreateRequest(taskSerialNumber, memberId, projectSerialNumber, content);
        projectRepository.insertComment(requestBody);
    }

    @Override
    public List<CommentDto> getComments(Long taskSerialNumber) {
        return projectRepository.findAllComments(taskSerialNumber);
    }

    @Override
    public CommentDto getComment(Long serialNumber) {
        return projectRepository.findComment(serialNumber);
    }

    @Override
    public void modifyComment(Long serialNumber, String content) {
        CommentModifyRequest requestBody = new CommentModifyRequest(serialNumber, content);
        projectRepository.updateComment(requestBody);
    }

    @Override
    public void deleteComment(Long serialNumber) {
        projectRepository.deleteComment(serialNumber);
    }
}
