package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.Apprentice;
import com.semenbazanov.fencingschoolfx.model.Training;
import com.semenbazanov.fencingschoolfx.retrofit.ApprenticeRepository;
import com.semenbazanov.fencingschoolfx.retrofit.TrainingRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class ApprenticeController implements ControllerData<Apprentice> {
    public TextField surname;
    public TextField name;
    public TextField patronymic;
    public TextField phoneNumber;
    public ListView<Training> trainingsListView;
    private Apprentice apprentice;

    @Override
    public void initData(Apprentice value) {
        this.apprentice = value;

        this.name.setText(this.apprentice.getName());
        this.surname.setText(this.apprentice.getSurname());
        this.patronymic.setText(this.apprentice.getPatronymic());
        this.phoneNumber.setText(String.valueOf(this.apprentice.getPhoneNumber()));

        try {
            this.initListView();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
        }
    }

    public void initListView() throws IOException {
        TrainingRepository repository = new TrainingRepository();
        List<Training> trainings = repository.get(this.apprentice.getId());
        this.trainingsListView.setItems(FXCollections.observableArrayList(trainings));
    }

    public void toUpdate(ActionEvent actionEvent) {
        this.apprentice.setSurname(this.surname.getText());
        this.apprentice.setName(this.name.getText());
        this.apprentice.setPatronymic(this.patronymic.getText());
        this.apprentice.setPhoneNumber(this.phoneNumber.getText());

        try {
            ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
            apprenticeRepository.update(this.apprentice);
            App.showAlert("Information", "Данные обновлены", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
        catch (IllegalArgumentException e){
            App.showAlert("Error", "Ученик уже существует", Alert.AlertType.ERROR);

        }
    }

    public void toDelete(ActionEvent actionEvent) {
        try {
            ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
            apprenticeRepository.delete(this.apprentice.getId());
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toAddTraining(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("add_training.fxml", "add training", this.apprentice);
            this.initListView();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toDeleteTraining(ActionEvent actionEvent) {
        Training training = this.trainingsListView.getSelectionModel().getSelectedItem();
        if (training == null) {
            App.showAlert("Error", "Тренировка не выбрана", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            TrainingRepository repository = new TrainingRepository();
            repository.delete(training.getId());

            this.initListView();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
