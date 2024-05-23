package com.semenbazanov.service;

import com.semenbazanov.model.Trainer;
import com.semenbazanov.model.TrainerSchedule;
import com.semenbazanov.repository.TrainerScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TrainerScheduleServiceImpl implements TrainerScheduleService {

    private TrainerScheduleRepository repository;

    private TrainerService trainerService;

    @Autowired
    public void setRepository(TrainerScheduleRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Override
    public TrainerSchedule add(long id, String dayWeek, LocalTime start, LocalTime end) {
        Trainer trainer = this.trainerService.get(id);

        TrainerSchedule schedule = trainer.getSchedule();
        if (schedule == null) {
            schedule = new TrainerSchedule(trainer);
        }
        schedule.set(dayWeek, start, end);
        try {
            this.repository.save(schedule);
            return schedule;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("TrainerSchedule already exists");
        }
    }

    @Override
    public TrainerSchedule get(long id) {
        return this.repository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Schedule doesn't exist"));
    }

    @Override
    public TrainerSchedule delete(long id, String dayWeek) {
        Trainer trainer = this.trainerService.get(id);

        TrainerSchedule schedule = trainer.getSchedule();
        if (schedule == null) {
            throw new IllegalArgumentException("TrainerSchedule doesn't exist");
        }

        schedule.set(dayWeek, null, null);
        try {
            this.repository.save(schedule);
            return schedule;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("TrainerSchedule already exists");
        }
    }
}
