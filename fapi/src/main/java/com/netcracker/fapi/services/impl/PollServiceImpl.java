package com.netcracker.fapi.services.impl;

import com.netcracker.fapi.services.PollService;
import com.netcracker.fapi.viewmodels.PollVM;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@NoArgsConstructor
public class PollServiceImpl implements PollService {
    private static final String URL = "http://localhost:8080/api/polls";
    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity findByUserId(@NotNull Long id, @NotNull String isDraft) {
        String path = URL + "/user/" + id + "?isDraft=" + isDraft;
        ArrayList models = restTemplate.getForObject(path, ArrayList.class);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    public ResponseEntity findAll(int page, int size) {
        String path = URL + "/admin?page=" + page + "&size=" + size;
        ArrayList models = restTemplate.getForObject(path, ArrayList.class);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    private void setLink(PollVM pollVM) {
        if ((pollVM.getLink() == null || pollVM.getLink().isEmpty()) &&
                !pollVM.getIsDraft()) {
            pollVM.setLink("localhost:4200/survey/" + UUID.randomUUID().toString());
        }
    }

    @Override
    public ResponseEntity findByLink(@NotNull String link) throws ChangeSetPersister.NotFoundException {
        PollVM model = restTemplate.getForObject(URL + "/?link=" + link, PollVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity findById(@NotNull Long id) throws ChangeSetPersister.NotFoundException {
        PollVM model = restTemplate.getForObject(URL + "/" + id, PollVM.class);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity create(@Valid PollVM pollVM) {
        setLink(pollVM);
        HttpEntity<PollVM> entity = new HttpEntity<>(pollVM);
        return restTemplate.postForEntity(URL, entity, PollVM.class);
    }
}
