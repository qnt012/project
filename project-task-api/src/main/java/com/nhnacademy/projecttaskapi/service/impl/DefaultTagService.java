package com.nhnacademy.projecttaskapi.service.impl;

import com.nhnacademy.projecttaskapi.repository.TagRepository;
import com.nhnacademy.projecttaskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTagService implements TagService {
    private final TagRepository tagRepository;
}
