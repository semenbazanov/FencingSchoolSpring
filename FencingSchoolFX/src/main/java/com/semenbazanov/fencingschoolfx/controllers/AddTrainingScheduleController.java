package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerScheduleRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddTrainingScheduleController implements ControllerData<Trainer> {
    public ComboBox<String> dayWeek;
    public ComboBox<LocalTime> timeStartComboBox;
    public ComboBox<LocalTime> timeEndComboBox;
    private Trainer trainer;

    @Override
    public void initData(Trainer value) {
        this.trainer = value;

        ArrayList<String> days = new ArrayList<>();
        days.add("monday");
        days.add("tuesday");
        days.add("wednesday");
        days.add("thursday");
        days.add("friday");
        days.add("saturday");
        days.add("sunday");
        this.dayWeek.setItems(FXCollections.observableArrayList(days));

        fillComboBox(this.timeStartComboBox, this.timeEndComboBox);

        this.timeStartComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int stepMinutes = 90;
                LocalTime selectedStartTime = newValue.plusMinutes(stepMinutes);
                LocalTime endTime = LocalTime.of(21, 30);
                this.timeEndComboBox.getItems().clear();

                List<LocalTime> timeSlots = new ArrayList<>();
                while (!selectedStartTime.isAfter(endTime)) {
                    timeSlots.add(selectedStartTime);
                    selectedStartTime = selectedStartTime.plusMinutes(stepMinutes);
                }
                this.timeEndComboBox.getItems().addAll(timeSlots);
            }
        });
    }

    public void fillComboBox(ComboBox<LocalTime> timeStartComboBox, ComboBox<LocalTime> timeEndComboBox) {
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(20, 0);
        int stepMinutes = 90;

        List<LocalTime> timeSlots = new ArrayList<>();
        while (!startTime.isAfter(endTime)) {
            timeSlots.add(startTime);
            startTime = startTime.plusMinutes(stepMinutes);
        }
        timeStartComboBox.getItems().addAll(timeSlots);

        startTime = LocalTime.of(9, 30);
        endTime = LocalTime.of(21, 30);
        timeSlots = new ArrayList<>();
        while (!startTime.isAfter(endTime)) {
            timeSlots.add(startTime);
            startTime = startTime.plusMinutes(stepMinutes);
        }
        timeEndComboBox.getItems().addAll(timeSlots);
    }

    public void toAdd(ActionEvent actionEvent) {
        TrainerScheduleRepository repository = new TrainerScheduleRepository();
        String day = this.dayWeek.getSelectionModel().getSelectedItem();
        LocalTime start = this.timeStartComboBox.getSelectionModel().getSelectedItem();
        LocalTime end = this.timeEndComboBox.getSelectionModel().getSelectedItem();
        try {
            repository.post(this.trainer.getId(), day, start, end);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
