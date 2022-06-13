package com.nhnacademy.projectaccountapi.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "members")
public class Member {
    @Id
    private String id;

    private String password;

    private String email;

    private String state;
}
