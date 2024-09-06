package com.study.subject.testdomain.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateRequest {
    private String title;
    private String content;
}
