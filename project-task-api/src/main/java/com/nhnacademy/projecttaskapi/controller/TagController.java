package com.nhnacademy.projecttaskapi.controller;

import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tags")
public class TagController {
    private final TagService tagService;

    @GetMapping("{serialNumber}")
    public ResponseEntity<TagDto> getTag(@PathVariable Long serialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tagService.getTag(serialNumber));
    }

    @GetMapping("list/{projectSerialNumber}")
    public ResponseEntity<List<TagDto>> getTags(@PathVariable Long projectSerialNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tagService.getTags(projectSerialNumber));
    }

    @PostMapping("/insert")
    public ResponseEntity<Tag> postTagCreate(@RequestBody TagCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tagService.createTag(request));
    }

    @PutMapping("/update")
    public ResponseEntity<Tag> putTag(@RequestBody TagModifyRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tagService.modifyTag(request));
    }

    @DeleteMapping("/delete/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTag(@PathVariable Long serialNumber) {
        tagService.removeTag(serialNumber);
    }
}
