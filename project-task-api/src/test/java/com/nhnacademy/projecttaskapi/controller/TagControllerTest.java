package com.nhnacademy.projecttaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projecttaskapi.domain.dto.TagDto;
import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;
import com.nhnacademy.projecttaskapi.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TagController.class)
class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getTag() throws Exception {
        Long serialNumber = 1L;
        TagDto tagDto = new TagDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "태그";
            }
        };
        when(tagService.getTag(serialNumber)).thenReturn(tagDto);
        mockMvc.perform(get("/tags/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(tagDto.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(tagDto.getName()))
                .andDo(print());
        verify(tagService).getTag(serialNumber);
    }

    @Test
    void getTags() throws Exception {
        Long projectSerialNumber = 1L;
        TagDto tagDto = new TagDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "태그";
            }
        };
        List<TagDto> Tags = List.of(tagDto);
        when(tagService.getTags(projectSerialNumber)).thenReturn(Tags);
        mockMvc.perform(get("/tags/list/{projectSerialNumber}", projectSerialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(tagService).getTags(projectSerialNumber);
    }

    @Test
    void postTagCreate() throws Exception {
        TagCreateRequest request = new TagCreateRequest(1L, "태그");
        Tag tag = new Tag(1L, null, request.getName());
        when(tagService.createTag(request)).thenReturn(tag);
        mockMvc.perform(post("/tags/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(tag.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(tag.getName()))
                .andDo(print());
        verify(tagService).createTag(request);
    }

    @Test
    void putTag() throws Exception {
        TagModifyRequest request = new TagModifyRequest(1L, "마일스톤 수정");
        Tag tag = new Tag(request.getSerialNumber(), null, request.getName());
        when(tagService.modifyTag(request)).thenReturn(tag);
        mockMvc.perform(put("/tags/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(tag.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(tag.getName()))
                .andDo(print());
        verify(tagService).modifyTag(request);
    }

    @Test
    void deleteTag() throws Exception {
        Long serialNumber = 1L;
        mockMvc.perform(delete("/tags/delete/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andDo(print());
        verify(tagService).removeTag(serialNumber);
    }
}