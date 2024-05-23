package com.semenbazanov.service;

import com.semenbazanov.model.TrainerSchedule;

import java.time.LocalTime;

public interface TrainerScheduleService {
    TrainerSchedule add(long id, String dayWeek, LocalTime start, LocalTime end);

    TrainerSchedule get(long id);

    TrainerSchedule delete(long id, String dayWeek);
}
