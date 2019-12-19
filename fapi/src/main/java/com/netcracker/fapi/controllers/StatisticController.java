package com.netcracker.fapi.controllers;

import com.netcracker.fapi.services.impl.StatisticServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/statistic")
@NoArgsConstructor
public class StatisticController {
    private StatisticServiceImpl service;

    @Autowired
    public StatisticController(StatisticServiceImpl service) {
        this.service = service;
    }

    @GetMapping("polls/{id}")
    public ResponseEntity getStatisticByPollId(@PathVariable @NotNull Long id) {
        return service.getStatistic(id);
    }
}
