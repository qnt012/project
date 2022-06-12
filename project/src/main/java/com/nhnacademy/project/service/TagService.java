package com.nhnacademy.project.service;


import com.nhnacademy.project.entity.Tag;

import java.util.List;

public interface TagService {
    Tag getTag(Long serialNumber);
    List<Tag> getTags(Long projectSerialNumber);
    void createTag(Long projectSerialNumber, String name);
    void modifyTag(Long serialNumber, String name);
    void deleteTag(Long serialNumber);
}
