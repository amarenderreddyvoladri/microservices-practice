package com.example.demo.files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessEvent {
    private Long userId;
    private String userName;
    private String accessedAt;
}