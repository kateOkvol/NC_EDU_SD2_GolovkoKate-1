package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletedQuestionVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    @Size(max=500, message = "Answer must contain less than 500 symbols")
    private String answer;

    @NotNull
    @Digits(integer = 13, fraction = 0)
    private Long questionId;
}
