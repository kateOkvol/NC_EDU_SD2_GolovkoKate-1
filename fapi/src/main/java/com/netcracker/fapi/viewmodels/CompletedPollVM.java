package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedPollVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    @NotNull
    @Digits(integer = 13, fraction = 0)
    private Long pollId;

    @NotNull
    private List<CompletedQuestionVM> questions;
}
