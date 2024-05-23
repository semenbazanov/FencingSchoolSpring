package com.semenbazanov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer_schedule")
public class TrainerSchedule {
    @Id
    @Column(name = "trainer_id")
    private long id;

    private LocalTime mondayStart;

    private LocalTime mondayEnd;

    private LocalTime tuesdayStart;

    private LocalTime tuesdayEnd;

    private LocalTime wednesdayStart;

    private LocalTime wednesdayEnd;

    private LocalTime thursdayStart;

    private LocalTime thursdayEnd;

    private LocalTime fridayStart;

    private LocalTime fridayEnd;

    private LocalTime saturdayStart;

    private LocalTime saturdayEnd;

    private LocalTime sundayStart;

    private LocalTime sundayEnd;

    @ToString.Exclude
    @OneToOne
    @MapsId
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    public TrainerSchedule(Trainer trainer) {
        this.trainer = trainer;
    }

    public void set(String dayWeek, LocalTime start, LocalTime end) {
        try {
            this.getClass().getDeclaredField(dayWeek + "Start").set(this, start);
            this.getClass().getDeclaredField(dayWeek + "End").set(this, end);
        } catch (IllegalAccessException ignored) {
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Неверный день");
        }
    }

    public LocalTime[] getTime(String dayWeek) {
        try {
            LocalTime start = (LocalTime) this.getClass().getDeclaredField(dayWeek + "Start").get(this);
            LocalTime end = (LocalTime) this.getClass().getDeclaredField(dayWeek + "End").get(this);
            return new LocalTime[]{start, end};
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
        return null;
    }

    public boolean getDate(String dayWeek){
        try {
            LocalTime start = (LocalTime) this.getClass().getDeclaredField(dayWeek + "Start").get(this);
            LocalTime end = (LocalTime) this.getClass().getDeclaredField(dayWeek + "End").get(this);
            if (start != null && end != null){
                return true;
            }
        } catch (IllegalAccessException | NoSuchFieldException ignored) {
        }
        return false;
    }
}
