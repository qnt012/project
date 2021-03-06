package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.CommentDto;
import com.nhnacademy.projecttaskapi.domain.request.CommentCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.CommentModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Comment;
import com.nhnacademy.projecttaskapi.exception.ValidationFailedException;
import com.nhnacademy.projecttaskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{serialNumber}")
    public CommentDto getComment(@PathVariable Long serialNumber) {
        return commentService.getComment(serialNumber);
    }

    @GetMapping("/list/{taskSerialNumber}")
    public List<CommentDto> getComments(@PathVariable Long taskSerialNumber) {
        return commentService.getComments(taskSerialNumber);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment postCommentCreate(@Valid @RequestBody CommentCreateRequest request,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return commentService.createComment(request);
    }

    @PutMapping("/update")
    public Comment putComment(@Valid @RequestBody CommentModifyRequest request,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return commentService.modifyComment(request);
    }

    @DeleteMapping("/delete/{serialNumber}")
    public void deleteComment(@PathVariable Long serialNumber) {
        commentService.deleteComment(serialNumber);
    }
}
