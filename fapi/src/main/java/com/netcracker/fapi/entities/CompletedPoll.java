package com.netcracker.fapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletedPoll {
    private Long id;
    private Long pollId;
    private List<CompletedQuestion> questions;
}
