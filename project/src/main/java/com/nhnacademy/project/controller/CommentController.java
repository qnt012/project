package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("{projectSerialNumber}/{taskSerialNumber}/create")
    public String postCommentCreate(@PathVariable Long projectSerialNumber,
                                    @PathVariable Long taskSerialNumber,
                                    @RequestParam("content") String content,
                                    Principal principal) {
        commentService.createComment(taskSerialNumber, principal.getName(), projectSerialNumber, content);
        return "redirect:/tasks/"+projectSerialNumber+"/view/"+taskSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/{taskSerialNumber}/modify/{serialNumber}")
    public String getCommentModify(@PathVariable Long projectSerialNumber,
                                   @PathVariable Long taskSerialNumber,
                                   @PathVariable Long serialNumber,
                                   ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("taskSerialNumber", taskSerialNumber);
        modelMap.put("comment", commentService.getComment(serialNumber));
        return "project/commentModifyForm";
    }

    @PostMapping("{projectSerialNumber}/{taskSerialNumber}/modify/{serialNumber}")
    public String postCommentModify(@PathVariable Long projectSerialNumber,
                                   @PathVariable Long taskSerialNumber,
                                   @PathVariable Long serialNumber,
                                    @RequestParam("content") String content) {
        commentService.modifyComment(serialNumber, content);
        return "redirect:/tasks/"+projectSerialNumber+"/view/"+taskSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/{taskSerialNumber}/delete/{serialNumber}")
    public String getCommentDelete(@PathVariable Long projectSerialNumber,
                                    @PathVariable Long taskSerialNumber,
                                    @PathVariable Long serialNumber) {
        commentService.deleteComment(serialNumber);
        return "redirect:/tasks/"+projectSerialNumber+"/view/"+taskSerialNumber;
    }
}
