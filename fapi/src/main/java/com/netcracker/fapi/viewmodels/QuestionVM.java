package com.netcracker.fapi.viewmodels;

import com.netcracker.fapi.entities.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVM {
    @Digits(integer = 13, fraction = 0)
    private Long id;

    @NotNull
    private QuestionType type;

    @NotBlank(message = "Question text is required")
    @Size(max = 250, message = "Question must contain less than 250 symbols")
    private String name;

    @Nullable
    private List<AnswerVM> answers;

    private Boolean isRequired;

    private Boolean isDeleted = false;
}
