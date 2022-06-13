package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.TaskDto;
import com.nhnacademy.projecttaskapi.domain.dto.TaskTagDto;
import com.nhnacademy.projecttaskapi.domain.request.TaskCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TaskModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Task;
import com.nhnacademy.projecttaskapi.exception.ValidationFailedException;
import com.nhnacademy.projecttaskapi.service.TaskService;
import com.nhnacademy.projecttaskapi.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskTagService taskTagService;

    @GetMapping("{serialNumber}")
    public TaskDto getTask(@PathVariable Long serialNumber){
        return taskService.getTask(serialNumber);
    }

    @GetMapping("/tags/{serialNumber}")
    public List<TaskTagDto> getTaskTags(@PathVariable Long serialNumber) {
        return taskTagService.getTaskTags(serialNumber);
    }

    @GetMapping("/list/{projectSerialNumber}")
    public List<TaskDto> getTasks(@PathVariable Long projectSerialNumber) {
        return taskService.getTasks(projectSerialNumber);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Task postTaskCreate(@Valid @RequestBody TaskCreateRequest request,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return taskService.createTask(request);

    }

    @PutMapping("/update")
    public Task putTask(@Valid @RequestBody TaskModifyRequest request,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return taskService.updateTask(request);
    }

    @DeleteMapping("/delete/{serialNumber}")
    public void deleteTask(@PathVariable Long serialNumber) {
        taskService.deleteTask(serialNumber);
    }

}
