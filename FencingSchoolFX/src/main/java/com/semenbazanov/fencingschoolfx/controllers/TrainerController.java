package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import com.semenbazanov.fencingschoolfx.model.TrainerSchedule;
import com.semenbazanov.fencingschoolfx.model.TrainerScheduleItem;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerRepository;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerScheduleRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainerController implements ControllerData<Trainer> {
    public TableView<TrainerScheduleItem> scheduleView;
    public TextField surname;
    public TextField name;
    public TextField patronymic;
    public TextField experience;
    private Trainer trainer;

    @Override
    public void initData(Trainer value) {
        this.trainer = value;

        this.name.setText(this.trainer.getName());
        this.surname.setText(this.trainer.getSurname());
        this.patronymic.setText(this.trainer.getPatronymic());
        this.experience.setText(String.valueOf(this.trainer.getExperience()));

        try {
            this.initTable();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void initTable() throws IOException {
        TableColumn<TrainerScheduleItem, String> dayColumn = new TableColumn<>("День недели");
        TableColumn<TrainerScheduleItem, LocalTime> startTimeColumn = new TableColumn<>("Время начала работы");
        TableColumn<TrainerScheduleItem, LocalTime> endTimeColumn = new TableColumn<>("Время окончания работы");
        this.scheduleView.getColumns().setAll(dayColumn, startTimeColumn, endTimeColumn);

        dayColumn.setCellValueFactory(new PropertyValueFactory<>("engDay"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        TrainerScheduleRepository repository = new TrainerScheduleRepository();
        try{
            TrainerSchedule trainerSchedule = repository.get(trainer.getId());
            List<TrainerScheduleItem> trainerScheduleItems = trainerSchedule.get();
            this.scheduleView.setItems(FXCollections.observableArrayList(trainerScheduleItems));
        }
        catch (IllegalArgumentException e){
            this.scheduleView.setItems(FXCollections.observableArrayList(new ArrayList<>()));
        }
    }

    public void toUpdateTrainer(ActionEvent actionEvent) {
        this.trainer.setName(this.name.getText());
        this.trainer.setPatronymic(this.patronymic.getText());
        this.trainer.setExperience(Integer.parseInt(this.experience.getText()));
        try {
            TrainerRepository trainerRepository = new TrainerRepository();
            trainerRepository.update(this.trainer);
            App.showAlert("Information", "Данные обновлены", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toDeleteTrainer(ActionEvent actionEvent) {
        try {
            TrainerRepository trainerRepository = new TrainerRepository();
            trainerRepository.delete(this.trainer.getId());
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toAddTraining(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("add_training_schedule.fxml", "add training schedule", this.trainer);
            this.initTable();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toDeleteTraining(ActionEvent actionEvent) {
        TrainerScheduleRepository repository = new TrainerScheduleRepository();
        TrainerScheduleItem schedule = this.scheduleView.getSelectionModel().getSelectedItem();
        if (schedule == null) {
            App.showAlert("Error", "Запись тренировки не выбрана", Alert.AlertType.INFORMATION);
            return;
        }
        String dayWeek = schedule.getEngDay();
        try {
            repository.delete(this.trainer.getId(), dayWeek);
            this.initTable();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
