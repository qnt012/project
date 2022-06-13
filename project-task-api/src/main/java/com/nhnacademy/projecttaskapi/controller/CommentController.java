package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;
import com.nhnacademy.projecttaskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{serialNumber}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long serialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentService.getComment(serialNumber));
    }

    @GetMapping("/list/{taskSerialNumber}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long taskSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentService.getComments(taskSerialNumber));
    }

    @PostMapping("/insert")
    public ResponseEntity<Comment> postCommentCreate(@RequestBody CommentCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentService.createComment(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> putComment(@RequestBody CommentModifyRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(commentService.modifyComment(request));
    }

    @DeleteMapping("/delete/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long serialNumber) {
        commentService.deleteComment(serialNumber);
    }
}
