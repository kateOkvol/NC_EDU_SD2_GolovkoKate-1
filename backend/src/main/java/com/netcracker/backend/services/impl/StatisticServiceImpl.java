package com.netcracker.backend.services.impl;

import com.netcracker.backend.entities.CompletedPoll;
import com.netcracker.backend.entities.CompletedQuestion;
import com.netcracker.backend.entities.Statistic;
import com.netcracker.backend.repositories.CompletedPollRepository;
import com.netcracker.backend.repositories.CompletedQuestionRepository;
import com.netcracker.backend.services.StatisticService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private CompletedPollRepository pollRepository;

    @Autowired
    public StatisticServiceImpl(CompletedPollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Override
    public List<Statistic> getStatistic(@NotNull Long pollId) {
        HashMap<Long, List<Statistic>> statisticMap = calculate(pollId);
        List<Statistic> result = new ArrayList<>();
        statisticMap.forEach((key, value) -> result.addAll(value));
        return result;
    }

    private HashMap<Long, List<Statistic>> calculate(Long pollId) {
        HashMap<Long, List<Statistic>> statisticMap = new HashMap<>();
        List<CompletedQuestion> currentList = new ArrayList<>();

        List<CompletedPoll> polls = pollRepository.findAllByPollId(pollId);
        polls.forEach(poll -> currentList.addAll(poll.getQuestions()));

        currentList.forEach(currentQuestion -> {
            List<Statistic> answers = new ArrayList<>();
            if (statisticMap.containsKey(currentQuestion.getQuestionId())) {
                answers = statisticMap.get(currentQuestion.getQuestionId());
                boolean isNew = true;
                for (Statistic answer : answers) {
                    if (answer.getName().equals(currentQuestion.getAnswer())) {
                        answer.setValue(answer.getValue() + 1L);
                        isNew = false;
                    }
                }
                if (isNew) {
                    answers.add(new Statistic(currentQuestion.getAnswer(), 1L, currentQuestion.getQuestionId()));
                }
            } else {
                answers.add(new Statistic(currentQuestion.getAnswer(), 1L, currentQuestion.getQuestionId()));
            }
            statisticMap.put(currentQuestion.getQuestionId(), answers);
        });
        return statisticMap;
    }

}
