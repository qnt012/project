package com.nhnacademy.project.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Member {
    private String id;
    private String password;
    private String email;
    private String state;
}
