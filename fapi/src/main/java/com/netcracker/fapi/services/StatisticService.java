package com.netcracker.fapi.services;

import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface StatisticService {
    ResponseEntity getStatistic(@NotNull Long pollId);
}
