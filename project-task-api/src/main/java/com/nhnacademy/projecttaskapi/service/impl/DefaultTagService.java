package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.exception.ProjectNotFoundException;
import com.nhnacademy.projecttaskapi.exception.TagNotFoundException;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import com.nhnacademy.projecttaskapi.repository.TagRepository;
import com.nhnacademy.projecttaskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService {
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Override
    public Tag createTag(TagCreateRequest request) {
        Project project = projectRepository.findById(request.getProjectSerialNumber()).orElseThrow(ProjectNotFoundException::new);
        Tag tag = new Tag(null, project, request.getName());
        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag modifyTag(TagModifyRequest request) {
        Tag tag = tagRepository.findById(request.getSerialNumber()).orElseThrow(TagNotFoundException::new);
        tag.setName(request.getName());
        return tagRepository.save(tag);
    }

    @Override
    public void removeTag(Long serialNumber) {
        tagRepository.deleteById(serialNumber);
    }
}
