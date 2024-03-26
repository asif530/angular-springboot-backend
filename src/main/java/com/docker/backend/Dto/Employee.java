package com.docker.backend.Dto;

import lombok.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Long age;
}
