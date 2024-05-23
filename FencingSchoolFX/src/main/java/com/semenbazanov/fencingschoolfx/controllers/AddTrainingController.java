package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.*;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerRepository;
import com.semenbazanov.fencingschoolfx.retrofit.TrainingRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddTrainingController implements ControllerData<Apprentice> {
    public ComboBox<Trainer> trainerComboBox;
    public DatePicker date;
    public ComboBox<LocalTime> timeComboBox;
    public TextField numberGym;

    private Apprentice apprentice;

    @Override
    public void initData(Apprentice value) {
        this.apprentice = value;
        this.date.setDisable(true);
        this.timeComboBox.setDisable(true);

        try {
            this.fillComboBox(this.trainerComboBox);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }

        //настройка callback при выборе тренера в комбобоксе
        this.trainerComboBox.setOnAction(event -> {
            Trainer trainer = trainerComboBox.getSelectionModel().getSelectedItem();
            if (trainer != null) {
                this.date.setDisable(false);
                this.timeComboBox.setDisable(true);

                TrainingRepository repository = new TrainingRepository();
                List<LocalDate> freeDate = new ArrayList<>();
                try {
                    freeDate.addAll(repository.getFreeDate(trainer.getId()));
                } catch (IOException e) {
                    App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                }
                updateDatePicker(freeDate);
            }
        });

        //настройка Listener, чтобы следить за выбором тренера в комбобоксе
        this.date.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                this.timeComboBox.getItems().clear();
                Trainer trainer = this.trainerComboBox.getSelectionModel().getSelectedItem();
                if (trainer != null) {
                    try {
                        this.updateTimeComboBox(trainer);
                        this.timeComboBox.setDisable(false);
                    } catch (IOException e) {
                        App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                    }
                }
            }
        });
    }

    private void updateDatePicker(List<LocalDate> freeDate) {
        this.date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (!freeDate.contains(item)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb");
                }
            }
        });
    }

    public void updateTimeComboBox(Trainer trainer) throws IOException {
        //задаем формат даты в String
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateString = dateTimeFormatter.format(this.date.getValue());

        //получаем слоты с сервера
        TrainingRepository repository = new TrainingRepository();
        List<LocalTime> timeSlots = repository.get(trainer.getId(), dateString);
        this.timeComboBox.getItems().addAll(timeSlots);
    }

    public void toAdd(ActionEvent actionEvent) {
        Trainer trainer = this.trainerComboBox.getSelectionModel().getSelectedItem();
        if (trainer == null) {
            App.showAlert("Error", "Тренер не выбран", Alert.AlertType.ERROR);
            return;
        }

        String numberGymText = this.numberGym.getText();
        if (numberGymText.isEmpty()) {
            App.showAlert("Error", "Введите номер зала", Alert.AlertType.ERROR);
            return;
        }
        int numberGym = Integer.parseInt(this.numberGym.getText());

        LocalDate localDate = this.date.getValue();
        if (localDate == null) {
            App.showAlert("Error", "Выберите дату", Alert.AlertType.ERROR);
            return;
        }
        LocalTime time = this.timeComboBox.getSelectionModel().getSelectedItem();
        if (time == null) {
            App.showAlert("Error", "Выберите время", Alert.AlertType.ERROR);
            return;
        }
        Training training = new Training(numberGym, localDate, time);

        TrainingRepository repository = new TrainingRepository();
        try {
            repository.post(trainer.getId(), this.apprentice.getId(), training);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void fillComboBox(ComboBox<Trainer> comboBox) throws IOException {
        TrainerRepository repository = new TrainerRepository();
        List<Trainer> trainers = repository.get();
        comboBox.setItems(FXCollections.observableArrayList(trainers));
    }
}
