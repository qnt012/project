package com.nhnacademy.project.service.impl;

import com.nhnacademy.project.domain.dto.TagDto;
import com.nhnacademy.project.repository.ProjectRepository;
import com.nhnacademy.project.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService {
    private final ProjectRepository projectRepository;


    @Override
    public TagDto getTag(Long serialNumber) {
        return projectRepository.findTag(serialNumber);
    }

    @Override
    public List<TagDto> getTags(Long projectSerialNumber) {
        return projectRepository.findAllTags(projectSerialNumber);
    }

    @Override
    public void createTag(Long projectSerialNumber, String name) {
        projectRepository.insertTag(projectSerialNumber, name);
    }

    @Override
    public void modifyTag(Long serialNumber, String name) {
        projectRepository.updateTag(serialNumber, name);
    }

    @Override
    public void deleteTag(Long serialNumber) {
        projectRepository.deleteTag(serialNumber);
    }
}
