package com.netcracker.fapi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private Long id;
    private Question question;
    private String text;
    private Boolean isSelected = false;
    private Boolean isDeleted;
}
