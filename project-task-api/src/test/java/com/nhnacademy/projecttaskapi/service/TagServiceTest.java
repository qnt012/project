package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.entity.Project;
import com.nhnacademy.projecttaskapi.repository.ProjectRepository;
import com.nhnacademy.projecttaskapi.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TagServiceTest {

    @Autowired
    private TagService tagService;

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private TagRepository tagRepository;

    private Project project;
    private Tag tag;

    @BeforeEach
    void setUp() {
        project = new Project(1L, "memberId", "project", "활성");
        tag = new Tag(1L, project, "태그");
    }
    
    @Test
    void createTag() {
        TagCreateRequest request = new TagCreateRequest(1L, "태그");

        Tag beforeSave = new Tag(null, project, request.getName());
        when(projectRepository.findById(request.getProjectSerialNumber())).thenReturn(Optional.of(project));
        when(tagRepository.save(beforeSave)).thenReturn(tag);

        Tag result = tagService.createTag(request);
        assertThat(result).isEqualTo(tag);

        verify(projectRepository).findById(request.getProjectSerialNumber());
        verify(tagRepository).save(beforeSave);
    }

    @Test
    void modifyTag() {
        TagModifyRequest request = new TagModifyRequest(1L, "태그수정");
        Tag modifiedTag = new Tag(1L, project, request.getName());

        when(tagRepository.findById(request.getSerialNumber())).thenReturn(Optional.of(tag));
        when(tagRepository.save(modifiedTag)).thenReturn(modifiedTag);

        Tag result = tagService.modifyTag(request);
        assertThat(result).isEqualTo(modifiedTag);

        verify(tagRepository).findById(request.getSerialNumber());
        verify(tagRepository).save(modifiedTag);
    }

    @Test
    void removeTag() {
        Long serialNumber = 1L;
        tagService.removeTag(serialNumber);

        verify(tagRepository).deleteById(serialNumber);
    }

    @Test
    void getTags() {
        Long projectSerialNumber = 1L;
        TagDto TagDto = new TagDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "태그";
            }
        };

        when(tagRepository.findAllByProjectSerialNumber(projectSerialNumber)).thenReturn(List.of(TagDto));

        List<TagDto> result = tagService.getTags(projectSerialNumber);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getSerialNumber()).isEqualTo(TagDto.getSerialNumber());
        assertThat(result.get(0).getName()).isEqualTo(TagDto.getName());

        verify(tagRepository).findAllByProjectSerialNumber(projectSerialNumber);

    }
}