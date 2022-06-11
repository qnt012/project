package com.nhnacademy.projecttaskapi.repository.custom;

import com.nhnacademy.projecttaskapi.entity.Project;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectRepositoryCustom {
    List<Project> findAllByMember(String memberId);
}
