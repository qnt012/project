package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;

import java.util.List;

public interface TagService {
    Tag createTag(TagCreateRequest request);

    Tag modifyTag(TagModifyRequest request);

    void removeTag(Long serialNumber);

    List<TagDto> getTags(Long projectSerialNumber);

    TagDto getTag(Long serialNumber);
}
