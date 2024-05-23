package com.semenbazanov.fencingschoolfx.model;

import java.util.Objects;

public class Trainer {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private int experience;

    public Trainer() {
    }

    public Trainer(String surname, String name, String patronymic, int experience) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.experience = experience;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return id == trainer.id && experience == trainer.experience && Objects.equals(surname, trainer.surname)
                && Objects.equals(name, trainer.name) && Objects.equals(patronymic, trainer.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, experience);
    }

    @Override
    public String toString() {
        return String.format("Фамилия: %s, Имя: %s, Отчество: %s, Опыт в годах: %d",
                getSurname(), getName(), getPatronymic(), getExperience());
    }
}
