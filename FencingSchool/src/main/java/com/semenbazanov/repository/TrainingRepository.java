package com.semenbazanov.repository;

import com.semenbazanov.model.Trainer;
import com.semenbazanov.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> getTrainingByTrainerId(long trainerId);

    List<Training> getTrainingByApprenticeId(long apprenticeId);

    List<Training> findAllByDateAndTrainer (LocalDate date, Trainer trainer);

    List<Training> findAllByDateAndNumberGym(LocalDate date, int numberGym);

}
