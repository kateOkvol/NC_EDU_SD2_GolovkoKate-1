package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollVM {

    @Digits(integer = 13, fraction = 0)
    private Long id;

    @NotNull
    @Digits(integer = 13, fraction = 0)
    private Long userId;

    @Size(max = 100, message = "Poll title must contain less than 100 symbols")
    private String title;

    private List<QuestionVM> questions;

    private Boolean isDraft = true;

    private String link;

    private Boolean isDeleted = false;
}
