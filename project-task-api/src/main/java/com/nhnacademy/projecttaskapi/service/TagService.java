package com.nhnacademy.projecttaskapi.service;

import com.nhnacademy.projecttaskapi.domain.request.TagCreateRequest;
import com.nhnacademy.projecttaskapi.domain.request.TagModifyRequest;
import com.nhnacademy.projecttaskapi.entity.Tag;

public interface TagService {
    Tag createTag(TagCreateRequest request);

    Tag modifyTag(TagModifyRequest request);

    void removeTag(Long serialNumber);
}
