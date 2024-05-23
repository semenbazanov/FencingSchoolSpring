package com.semenbazanov.controllers;

import com.semenbazanov.dto.ResponseResult;
import com.semenbazanov.model.Apprentice;
import com.semenbazanov.model.Trainer;
import com.semenbazanov.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Trainer>> post(@RequestBody Trainer trainer) {
        try {
            this.trainerService.add(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> get(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Trainer>>> get() {
        List<Trainer> trainers = this.trainerService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, trainers), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Trainer>> update(@RequestBody Trainer trainer) {
        try {
            Trainer update = this.trainerService.update(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> delete(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
