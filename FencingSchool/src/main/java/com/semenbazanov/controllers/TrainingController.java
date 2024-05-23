package com.semenbazanov.controllers;

import com.semenbazanov.dto.ResponseResult;
import com.semenbazanov.model.Training;
import com.semenbazanov.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    /*@PostMapping(path = {"/{trainerId}/{apprenticeId}"})
    public ResponseEntity<ResponseResult<Training>> post(@PathVariable long trainerId, @PathVariable long apprenticeId,
                                                         @RequestParam int numberGym, @RequestParam LocalDate date,
                                                         @RequestParam LocalTime timeStart) {
        try {
            Training training = this.trainingService.add(trainerId, apprenticeId, numberGym, date, timeStart);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping(path = {"/{trainerId}/{apprenticeId}"})
    public ResponseEntity<ResponseResult<Training>> post(@PathVariable long trainerId, @PathVariable long apprenticeId,
                                                         @RequestBody Training training) {
        try {
            Training add = this.trainingService.add(trainerId, apprenticeId, training);
            return new ResponseEntity<>(new ResponseResult<>(null, add), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> get(@PathVariable long id) {
        try {
            Training training = this.trainingService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/trainer/{trainerId}")
    public ResponseEntity<ResponseResult<List<Training>>> getByTrainerId(@PathVariable long trainerId) {
        List<Training> trainings = this.trainingService.getByTrainerId(trainerId);
        return new ResponseEntity<>(new ResponseResult<>(null, trainings), HttpStatus.OK);
    }

    @GetMapping(path = "/apprentice/{apprenticeId}")
    public ResponseEntity<ResponseResult<List<Training>>> getByApprenticeId(@PathVariable long apprenticeId) {
        List<Training> trainings = this.trainingService.getByApprenticeId(apprenticeId);
        return new ResponseEntity<>(new ResponseResult<>(null, trainings), HttpStatus.OK);
    }

    @GetMapping(path = "/time/{trainerId}")
    public ResponseEntity<ResponseResult<List<LocalTime>>>
    getFreeTimes(@PathVariable long trainerId, @DateTimeFormat(pattern = "dd.MM.yyyy") @RequestParam LocalDate date) {
        List<LocalTime> freeTimes = this.trainingService.getFreeTimes(trainerId, date);
        return new ResponseEntity<>(new ResponseResult<>(null, freeTimes), HttpStatus.OK);
    }

    @GetMapping(path = "/date/{trainerId}")
    public ResponseEntity<ResponseResult<List<LocalDate>>> getFreeDates(@PathVariable long trainerId) {
        List<LocalDate> freeDates = this.trainingService.getFreeDates(trainerId);
        return new ResponseEntity<>(new ResponseResult<>(null, freeDates), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> delete(@PathVariable long id) {
        try {
            Training training = this.trainingService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
