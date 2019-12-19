package com.netcracker.fapi.viewmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    @NotBlank
    @Size(max = 100, message = "Topic name must contain less than 100 symbols")
    private String name;

    private List<QuestionVM> questions;

    @NotNull
    private Boolean isShared = true;

    @NotNull
    @Digits(integer = 13, fraction = 0)
    private Long userId;
}
