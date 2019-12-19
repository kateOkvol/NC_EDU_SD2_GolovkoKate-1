package com.netcracker.fapi.services.impl;

import com.netcracker.fapi.services.TopicService;
import com.netcracker.fapi.viewmodels.TopicVM;
import lombok.NoArgsConstructor;
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
public class TopicServiceImpl implements TopicService {
    private static final String URL = "http://localhost:8080/api/topics";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity findByUserId(@NotNull Long id) {
        ArrayList model = restTemplate.getForObject(URL + "/user/" + id, ArrayList.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    public ResponseEntity findByAdmin() {
        ArrayList model = restTemplate.getForObject(URL + "/admin", ArrayList.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity findById(@NotNull Long id) {
        TopicVM model = restTemplate.getForObject(URL + "/" + id, TopicVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(@Valid TopicVM topicVM) {
        HttpEntity<TopicVM> entity = new HttpEntity<>(topicVM);
        ResponseEntity<TopicVM> vmResponse = restTemplate.postForEntity(URL, entity, TopicVM.class);
        return new ResponseEntity<>(vmResponse, HttpStatus.OK);
    }
}
