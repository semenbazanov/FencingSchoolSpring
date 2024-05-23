package com.semenbazanov.fencingschoolfx.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Training {
    private long id;
    private int numberGym;
    private Trainer trainer;
    private Apprentice apprentice;
    @JsonFormat(pattern = "dd.MM.yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private LocalTime timeStart;

    public Training() {
    }

    public Training(int numberGym, LocalDate date, LocalTime timeStart) {
        this.numberGym = numberGym;
        this.date = date;
        this.timeStart = timeStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumberGym() {
        return numberGym;
    }

    public void setNumberGym(int numberGym) {
        this.numberGym = numberGym;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Apprentice getApprentice() {
        return apprentice;
    }

    public void setApprentice(Apprentice apprentice) {
        this.apprentice = apprentice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return id == training.id && numberGym == training.numberGym
                && Objects.equals(trainer, training.trainer)
                && Objects.equals(apprentice, training.apprentice)
                && Objects.equals(date, training.date)
                && Objects.equals(timeStart, training.timeStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberGym, trainer, apprentice, date, timeStart);
    }

    @Override
    public String toString() {
        return String.format("Дата: %s %s, Тренер: %s, Номер зала %d",
                this.getDate().toString(), this.timeStart.toString(),
                this.trainer.getSurname(), this.numberGym);
    }
}
