package com.nhnacademy.projecttaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.projecttaskapi.domain.dto.MilestoneDto;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.MilestoneModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Milestone;
import com.nhnacademy.projecttaskapi.service.MilestoneService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MilestoneController.class)
class MilestoneControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MilestoneService milestoneService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getMilestone() throws Exception {
        Long serialNumber = 1L;
        MilestoneDto milestoneDto = new MilestoneDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "마일스톤";
            }
        };
        when(milestoneService.getMilestone(serialNumber)).thenReturn(milestoneDto);
        mockMvc.perform(get("/milestones/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(milestoneDto.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(milestoneDto.getName()))
                .andDo(print());
        verify(milestoneService).getMilestone(serialNumber);
    }

    @Test
    void getMilestones() throws Exception {
        Long projectSerialNumber = 1L;
        MilestoneDto milestoneDto = new MilestoneDto() {
            @Override
            public Long getSerialNumber() {
                return 1L;
            }

            @Override
            public String getName() {
                return "마일스톤";
            }
        };
        List<MilestoneDto> milestones = List.of(milestoneDto);
        when(milestoneService.getMilestones(projectSerialNumber)).thenReturn(milestones);
        mockMvc.perform(get("/milestones/list/{projectSerialNumber}", projectSerialNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andDo(print());
        verify(milestoneService).getMilestones(projectSerialNumber);
    }

    @Test
    void postMilestoneCreate() throws Exception {
        MilestoneCreateRequest request = new MilestoneCreateRequest(1L, "마일스톤");
        Milestone milestone = new Milestone(1L, null, request.getName());
        when(milestoneService.createMilestone(request)).thenReturn(milestone);
        mockMvc.perform(post("/milestones/insert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(milestone.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(milestone.getName()))
                .andDo(print());
        verify(milestoneService).createMilestone(request);
    }

    @Test
    void putMilestone() throws Exception {
        MilestoneModifyRequest request = new MilestoneModifyRequest(1L, "마일스톤 수정");
        Milestone milestone = new Milestone(request.getSerialNumber(), null, request.getName());
        when(milestoneService.modifyMilestone(request)).thenReturn(milestone);
        mockMvc.perform(put("/milestones/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.serialNumber").value(milestone.getSerialNumber()))
                .andExpect(jsonPath("$.name").value(milestone.getName()))
                .andDo(print());
        verify(milestoneService).modifyMilestone(request);
    }

    @Test
    void deleteMilestone() throws Exception {
        Long serialNumber = 1L;
        mockMvc.perform(delete("/milestones/delete/{serialNumber}", serialNumber))
                .andExpect(status().isOk())
                .andDo(print());
        verify(milestoneService).removeMilestone(serialNumber);
    }
}