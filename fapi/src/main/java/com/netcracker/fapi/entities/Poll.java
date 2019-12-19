package com.netcracker.fapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Poll {
    private Long id;
    private Long userId;
    private String title;
    private List<Question> questions;
    private Boolean isDraft = true;
    private String link;
    private Boolean isDeleted = false;
}
