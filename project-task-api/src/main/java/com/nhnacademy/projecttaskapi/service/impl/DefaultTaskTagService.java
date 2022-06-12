package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.domain.dto.TaskTagDto;
import com.nhnacademy.projecttaskapi.repository.TaskTagRepository;
import com.nhnacademy.projecttaskapi.service.TaskTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultTaskTagService implements TaskTagService {
    private final TaskTagRepository taskTagRepository;

    @Override
    public List<TaskTagDto> getTaskTags(Long serialNumber) {
        return taskTagRepository.findAllByPkTaskSerialNumber(serialNumber);
    }
}
