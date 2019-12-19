package com.netcracker.fapi.services.impl;

import com.netcracker.fapi.entities.Statistic;
import com.netcracker.fapi.services.StatisticService;
import com.netcracker.fapi.viewmodels.statistic.DataVM;
import com.netcracker.fapi.viewmodels.statistic.StatisticVM;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private static final String URL = "http://localhost:8080/api/statistic";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity getStatistic(@NotNull Long pollId) {
        String path = URL + "/polls/" + pollId;
        Statistic[] models = restTemplate.getForObject(path, Statistic[].class);

        List<Statistic> statisticList = Arrays.asList(models != null ? models : new Statistic[0]);

        Map<Long, List<DataVM>> statisticMap = new HashMap<>();
        statisticList.forEach(statistic -> {
            Long key = statistic.getQuestionId();
            DataVM data = new DataVM(statistic.getName(), statistic.getValue());
            if (statisticMap.containsKey(key)) {
                statisticMap.get(key).add(data);
            } else {
                List<DataVM> dataList = new ArrayList<>();
                dataList.add(data);
                statisticMap.put(key, dataList);
            }
        });

        List<StatisticVM> vmodels = new ArrayList<>();
        statisticMap.forEach((key, value) -> {
            vmodels.add(new StatisticVM(key, value));
        });

        return new ResponseEntity<>(vmodels, HttpStatus.OK);
    }

}
