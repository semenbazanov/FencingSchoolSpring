package com.semenbazanov.controllers;

import com.semenbazanov.dto.ResponseResult;
import com.semenbazanov.model.TrainerSchedule;
import com.semenbazanov.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@RestController
@RequestMapping("/trainer_schedule")
public class TrainerScheduleController {

    private TrainerScheduleService trainerScheduleService;

    @Autowired
    public void setTrainerService(TrainerScheduleService trainerScheduleService) {
        this.trainerScheduleService = trainerScheduleService;
    }


    @PostMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> post(@PathVariable long id, String dayWeek, LocalTime start,
                                                                LocalTime end) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.add(id, dayWeek, start, end);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{trainer_id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> get(@PathVariable long trainer_id) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.get(trainer_id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{trainer_id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> delete(@PathVariable long trainer_id, String dayWeek){
        try{
            TrainerSchedule schedule = this.trainerScheduleService.delete(trainer_id, dayWeek);
            return new ResponseEntity<>(new ResponseResult<>(null, schedule), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
