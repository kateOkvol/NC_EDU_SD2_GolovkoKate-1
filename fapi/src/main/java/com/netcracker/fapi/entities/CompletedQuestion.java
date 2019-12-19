package com.netcracker.fapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedQuestion {
    private Long id;
    private String answer;
    private Long questionId;
}
