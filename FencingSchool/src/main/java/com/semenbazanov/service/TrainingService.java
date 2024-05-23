package com.semenbazanov.service;

import com.semenbazanov.model.Training;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainingService {
    /*Training add(long trainerId, long apprenticeId, int numberGym,
                 LocalDate date, LocalTime timeStart);*/

    Training add(long trainerId, long apprenticeId, Training training);

    Training get(long id);

    List<Training> getByTrainerId(long trainerId);

    List<Training> getByApprenticeId(long apprenticeId);

    Training delete(long id);

    List<LocalTime> getFreeTimes(long trainerId, LocalDate date);

    List<LocalDate> getFreeDates(long trainerId);
}
