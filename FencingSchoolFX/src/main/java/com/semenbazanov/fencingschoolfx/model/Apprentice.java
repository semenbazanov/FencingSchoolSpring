package com.semenbazanov.fencingschoolfx.model;

import java.util.Objects;

public class Apprentice {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private String phoneNumber;

    public Apprentice() {
    }

    public Apprentice(String surname, String name, String patronymic, String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apprentice that = (Apprentice) o;
        return id == that.id && Objects.equals(surname, that.surname) && Objects.equals(name, that.name)
                && Objects.equals(patronymic, that.patronymic) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, phoneNumber);
    }

    @Override
    public String toString() {
        return String.format("Фамилия: %s, Имя: %s, Отчество: %s, Номер телефона: %s",
                getSurname(), getName(), getPatronymic(), getPhoneNumber());
    }
}
