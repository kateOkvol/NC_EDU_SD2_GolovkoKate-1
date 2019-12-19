package com.netcracker.fapi.services.impl;

import com.netcracker.fapi.entities.CompletedPoll;
import com.netcracker.fapi.mapping.CompletedPollMapping;
import com.netcracker.fapi.services.CompletedPollService;
import com.netcracker.fapi.viewmodels.CompletedPollVM;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Service
@NoArgsConstructor
public class CompletedPollServiceImpl implements CompletedPollService {
    private static final String URL = "http://localhost:8080/api/surveys";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity findById(@NotNull Long id) {
        CompletedPollVM model = restTemplate.getForObject(URL + "/" + id, CompletedPollVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    public ResponseEntity getByPollId(@NotNull Long id) {
        String path = URL + "/poll/" + id;
        ArrayList models = restTemplate.getForObject(path, ArrayList.class);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(@Valid CompletedPollVM completedPollVM) {
        HttpEntity<CompletedPollVM> entity = new HttpEntity<>(completedPollVM);
        ResponseEntity<CompletedPoll> vmResponse = restTemplate.postForEntity(URL, entity, CompletedPoll.class);
        return new ResponseEntity<>(vmResponse, HttpStatus.OK);
    }
}
