package com.netcracker.fapi.viewmodels.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticVM {
    private Long questionId;
    private List<DataVM> dataList;
}
