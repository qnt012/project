package com.nhnacademy.projecttaskapi.repository;

import com.nhnacademy.projecttaskapi.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
