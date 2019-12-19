package com.netcracker.fapi.services;

import com.netcracker.fapi.viewmodels.CompletedPollVM;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface CompletedPollService {

    ResponseEntity create(@Valid CompletedPollVM pollVM);
}
