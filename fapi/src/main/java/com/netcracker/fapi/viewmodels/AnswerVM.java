package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    @Digits(integer = 13, fraction = 0)
    private Long questionId;

    @NotBlank(message = "Answer text is required")
    @Size(max = 500, message = "Password must contain less than 100 symbols")
    private String text;

    private Boolean isSelected = false;

    private Boolean isDeleted = false;
}
