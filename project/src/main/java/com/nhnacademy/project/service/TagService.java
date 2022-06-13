package com.nhnacademy.project.service;


import com.nhnacademy.project.domain.dto.TagDto;

import java.util.List;

public interface TagService {
    TagDto getTag(Long serialNumber);
    List<TagDto> getTags(Long projectSerialNumber);
    void createTag(Long projectSerialNumber, String name);
    void modifyTag(Long serialNumber, String name);
    void deleteTag(Long serialNumber);
}
