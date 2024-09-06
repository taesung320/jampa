package com.study.subject.testdomain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResponse {
    private Long id;
    private String title;
    private String content;
}
