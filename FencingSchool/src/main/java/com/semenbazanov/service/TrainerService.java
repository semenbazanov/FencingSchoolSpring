package com.semenbazanov.service;

import com.semenbazanov.model.Trainer;

import java.util.List;

public interface TrainerService {
    /**
     * •	post – осуществляет добавление нового тренера в базу данных
     * •	isAvailable – осуществляет получение всех тренеров, а так же тренера по его id
     * •	put – осуществляет обновление тренера по его id
     * •	delete – осуществляет удаление тренера из базы данных по его id
     */
    void add(Trainer trainer);

    List<Trainer> get();

    Trainer get(long id);

    Trainer update(Trainer trainer);

    Trainer delete(long id);
}
