package com.netcracker.fapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private Long id;
    private String name;
    private Long userId;
    private List<Question> questions;
    private Boolean isShared;
    private Boolean isDeleted = false;
}
