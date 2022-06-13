package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.exception.CommentNotFoundException;
import com.nhnacademy.projecttaskapi.exception.ProjectMemberNotFoundException;
import com.nhnacademy.projecttaskapi.exception.TaskNotFoundException;
import com.nhnacademy.projecttaskapi.repository.CommentRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectMemberRepository;
import com.nhnacademy.projecttaskapi.repository.TaskRepository;
import com.nhnacademy.projecttaskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final ProjectMemberRepository projectMemberRepository;

    @Override
    public List<CommentDto> getComments(Long taskSerialNumber) {
        return commentRepository.findAllByTaskSerialNumber(taskSerialNumber);
    }

    @Override
    public Comment createComment(CommentCreateRequest request) {
        Task task = taskRepository.findById(request.getTaskSerialNumber()).orElseThrow(TaskNotFoundException::new);
        ProjectMember.Pk pk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        ProjectMember member = projectMemberRepository.findById(pk).orElseThrow(ProjectMemberNotFoundException::new);
        return commentRepository.save(new Comment(null, task, member, request.getContent()));
    }

    @Override
    public Comment modifyComment(CommentModifyRequest request) {
        Comment comment = commentRepository.findById(request.getSerialNumber()).orElseThrow(CommentNotFoundException::new);
        comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long serialNumber) {
        commentRepository.deleteById(serialNumber);
    }

    @Override
    public CommentDto getComment(Long serialNumber) {
        return commentRepository.findBySerialNumber(serialNumber).orElseThrow(CommentNotFoundException::new);
    }
}
