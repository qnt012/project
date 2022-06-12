package com.nhnacademy.project.controller;

import com.nhnacademy.project.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("tags")
public class TagController {
    private final TagService tagService;

    @GetMapping("{projectSerialNumber}")
    public String getTags(@PathVariable Long projectSerialNumber,
                                ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        modelMap.put("tags", tagService.getTags(projectSerialNumber));
        return "project/tagList";
    }

    @GetMapping("{projectSerialNumber}/create")
    public String getTagCreate(@PathVariable Long projectSerialNumber,
                                     ModelMap modelMap) {
        modelMap.put("projectSerialNumber", projectSerialNumber);
        return "project/tagCreateForm";
    }

    @PostMapping("{projectSerialNumber}/create")
    public String postTagCreate(@PathVariable Long projectSerialNumber,
                                      @RequestParam("name") String name) {

        tagService.createTag(projectSerialNumber, name);
        return "redirect:/tags/" + projectSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/modify/{serialNumber}")
    public String getTagModify(@PathVariable Long projectSerialNumber,
                                     @PathVariable Long serialNumber,
                                     ModelMap modelMap) {
        modelMap.put("tag", tagService.getTag(serialNumber));
        modelMap.put("projectSerialNumber", projectSerialNumber);
        return "project/tagModifyForm";
    }

    @PostMapping("{projectSerialNumber}/modify/{serialNumber}")
    public String postTagModify(@PathVariable Long projectSerialNumber,
                                      @PathVariable Long serialNumber,
                                      @RequestParam("name") String name) {
        tagService.modifyTag(serialNumber, name);
        return "redirect:/tags/" + projectSerialNumber;
    }

    @GetMapping("{projectSerialNumber}/delete/{serialNumber}")
    public String getTagDelete(@PathVariable Long projectSerialNumber,
                                     @PathVariable Long serialNumber) {
        tagService.deleteTag(serialNumber);
        return "redirect:/tags/" + projectSerialNumber;
    }
}
