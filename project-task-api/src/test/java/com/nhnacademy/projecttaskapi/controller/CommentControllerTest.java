package com.nhnacademy.projecttaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;
import com.nhnacademy.projecttaskapi.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getComment() throws Exception {
        Long serialNumber = 1L;
        CommentDto commentDto = new CommentDto() {
            @Override
            public Long getSerialNumber() {
                return serialNumber;
            }

            @Override
            public String getProjectMemberPkMemberId() {
                return "member";
            }

            @Override
            public String getContent() {
                return "content";
            }
        };
        when(commentService.getComment(serialNumber)).thenReturn(commentDto);
        mockMvc.perform(get("/comments/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(serialNumber))
                .andExpect(jsonPath("$.projectMemberPkMemberId").value(commentDto.getProjectMemberPkMemberId()))
                .andExpect(jsonPath("$.content").value(commentDto.getContent()))
                .andDo(print());
        verify(commentService).getComment(serialNumber);

    }

    @Test
    void getComments() throws Exception {
        Long taskSerialNumber = 1L;
        CommentDto commentDto = new CommentDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getProjectMemberPkMemberId() {
                return "member";
            }

            @Override
            public String getContent() {
                return "content";
            }
        };
        List<CommentDto> comments = List.of(commentDto);
        when(commentService.getComments(taskSerialNumber)).thenReturn(comments);
        mockMvc.perform(get("/comments/list/{taskSerialNumber}", taskSerialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(commentService).getComments(taskSerialNumber);
    }

    @Test
    void postCommentCreate() throws Exception {
        CommentCreateRequest request = new CommentCreateRequest(1L, "member", 1L, "content");
        Comment comment = new Comment(1L, null, null, request.getContent());
        when(commentService.createComment(request)).thenReturn(comment);
        mockMvc.perform(post("/comments/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(comment.getSerialNumber()))
                .andExpect(jsonPath("$.content").value(comment.getContent()))
                .andDo(print());
        verify(commentService).createComment(request);
    }

    @Test
    void putComment() throws Exception {
        CommentModifyRequest request = new CommentModifyRequest(1L, "content 수정");
        Comment comment = new Comment(request.getSerialNumber(), null, null, request.getContent());
        when(commentService.modifyComment(request)).thenReturn(comment);
        mockMvc.perform(put("/comments/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(comment.getSerialNumber()))
                .andExpect(jsonPath("$.content").value(comment.getContent()))
                .andDo(print());
        verify(commentService).modifyComment(request);
    }

    @Test
    void deleteComment() throws Exception {
        Long serialNumber = 1L;
        mockMvc.perform(delete("/comments/delete/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andDo(print());
        verify(commentService).deleteComment(serialNumber);
    }
}