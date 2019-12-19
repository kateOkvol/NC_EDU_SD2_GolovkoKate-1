package com.netcracker.backend.controllers;

import com.netcracker.backend.entities.Statistic;
import com.netcracker.backend.services.impl.StatisticServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
    public List<Statistic> getStatisticByPollId(@PathVariable @NotNull Long id) {
        return service.getStatistic(id);
    }
}
