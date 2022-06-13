package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;
import com.nhnacademy.projecttaskapi.entity.ProjectMember;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.repository.CommentRepository;
import com.nhnacademy.projecttaskapi.repository.ProjectMemberRepository;
import com.nhnacademy.projecttaskapi.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private ProjectMemberRepository projectMemberRepository;

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment(1L, null, null, "content");
    }

    @Test
    void getComments() {
        Long taskSerialNumber = 1L;
        Task task = new Task(taskSerialNumber, null, null, "title", "content");

        comment.setTask(task);
        CommentDto commentDto = new CommentDto() {
            @Override
            public Long getSerialNumber() {
                return comment.getSerialNumber();
            }

            @Override
            public String getProjectMemberPkMemberId() {
                return null;
            }

            @Override
            public String getContent() {
                return comment.getContent();
            }
        };

        when(commentRepository.findAllByTaskSerialNumber(taskSerialNumber)).thenReturn((List.of(commentDto)));

        List<CommentDto> result = commentService.getComments(taskSerialNumber);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(commentDto);

        verify(commentRepository).findAllByTaskSerialNumber(taskSerialNumber);
    }

    @Test
    void createComment() {
        CommentCreateRequest request = new CommentCreateRequest(1L, "member", 1L, "content");

        Task task = new Task(request.getTaskSerialNumber(), null, null, "title", "content");

        ProjectMember.Pk pk = new ProjectMember.Pk(request.getMemberId(), request.getProjectSerialNumber());
        ProjectMember projectMember = new ProjectMember(pk, null);
        comment.setTask(task);
        comment.setProjectMember(projectMember);

        Comment beforeSave = new Comment(null, task, projectMember, request.getContent());

        when(taskRepository.findById(request.getTaskSerialNumber())).thenReturn(Optional.of(task));
        when(projectMemberRepository.findById(pk)).thenReturn(Optional.of(projectMember));
        when(commentRepository.save(beforeSave)).thenReturn(comment);

        Comment result = commentService.createComment(request);

        assertThat(result).isEqualTo(comment);

        verify(taskRepository).findById(request.getTaskSerialNumber());
        verify(projectMemberRepository).findById(pk);
        verify(commentRepository).save(beforeSave);

    }

    @Test
    void modifyComment() {
        CommentModifyRequest request = new CommentModifyRequest(1L, "content2");
        Comment newComment = new Comment(request.getSerialNumber(), null, null, request.getContent());

        comment.setContent(request.getContent());
        when(commentRepository.findById(request.getSerialNumber())).thenReturn(Optional.of(comment));
        when(commentRepository.save(comment)).thenReturn(newComment);

        Comment result = commentService.modifyComment(request);
        assertThat(result).isEqualTo(newComment);

        verify(commentRepository).findById(request.getSerialNumber());
        verify(commentRepository).save(comment);
    }

    @Test
    void deleteComment() {
        Long serialNumber = 1L;
        commentService.deleteComment(serialNumber);
        verify(commentRepository).deleteById(serialNumber);
    }
}