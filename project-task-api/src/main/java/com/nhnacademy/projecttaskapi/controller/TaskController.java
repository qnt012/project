package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/list/{projectSerialNumber}")
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable Long projectSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskService.getTasks(projectSerialNumber));
    }

    @PostMapping("/insert")
    public ResponseEntity<Task> postTaskCreate(@RequestBody TaskCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskService.createTask(request));

    }
}
