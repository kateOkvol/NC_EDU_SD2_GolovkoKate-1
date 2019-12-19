package com.netcracker.backend.services;

import com.netcracker.backend.entities.Statistic;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface StatisticService {
//    List getStatistic(@NotNull Long pollId);
     List<Statistic> getStatistic(@NotNull Long pollId);
}
