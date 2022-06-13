package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.exception.ValidationFailedException;
import com.nhnacademy.projecttaskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
public class TagController {
    private final TagService tagService;

    @GetMapping("{serialNumber}")
    public TagDto getTag(@PathVariable Long serialNumber) {
        return tagService.getTag(serialNumber);
    }

    @GetMapping("list/{projectSerialNumber}")
    public List<TagDto> getTags(@PathVariable Long projectSerialNumber) {
        return tagService.getTags(projectSerialNumber);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag postTagCreate(@Valid @RequestBody TagCreateRequest request,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return tagService.createTag(request);
    }

    @PutMapping("/update")
    public Tag putTag(@Valid @RequestBody TagModifyRequest request,
                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        return tagService.modifyTag(request);
    }

    @DeleteMapping("/delete/{serialNumber}")
    public void deleteTag(@PathVariable Long serialNumber) {
        tagService.removeTag(serialNumber);
    }
}
