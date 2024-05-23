package com.semenbazanov.fencingschoolfx.model;

import java.time.LocalTime;
import java.util.Objects;

public class TrainerScheduleItem {
    private String rusDay;
    private String engDay;
    private LocalTime start;
    private LocalTime end;

    public TrainerScheduleItem() {
    }

    public TrainerScheduleItem(String engDay, LocalTime start, LocalTime end) {
        this.engDay = engDay;
        this.start = start;
        this.end = end;
    }

    public String getRusDay() {
        return rusDay;
    }

    public void setRusDay(String rusDay) {
        this.rusDay = rusDay;
    }

    public String getEngDay() {
        return engDay;
    }

    public void setEngDay(String engDay) {
        this.engDay = engDay;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerScheduleItem that = (TrainerScheduleItem) o;
        return Objects.equals(rusDay, that.rusDay) && Objects.equals(engDay, that.engDay)
                && Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rusDay, engDay, start, end);
    }

    @Override
    public String toString() {
        return "TrainerScheduleItem{" +
                "rusDay='" + rusDay + '\'' +
                ", engDay='" + engDay + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
