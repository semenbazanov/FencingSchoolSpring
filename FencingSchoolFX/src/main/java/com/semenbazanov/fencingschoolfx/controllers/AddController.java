package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.Apprentice;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import com.semenbazanov.fencingschoolfx.retrofit.ApprenticeRepository;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;

public class AddController {

    public RadioButton trainerButton;
    public RadioButton apprenticeButton;
    public TextField surname;
    public Label additionalLabel;
    public TextField name;
    public TextField patronymic;
    public TextField additionalTextField;

    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        this.trainerButton.setToggleGroup(group);
        this.apprenticeButton.setToggleGroup(group);
        this.apprenticeButton.setSelected(true);
    }

    public void toPutTrainer(ActionEvent actionEvent) {
        this.additionalLabel.setText("Опыт в годах");
    }

    public void toPutApprentice(ActionEvent actionEvent) {
        this.additionalLabel.setText("Номер телефона");
    }

    public void toCreate(ActionEvent actionEvent) {
        if (this.apprenticeButton.isSelected()) {
            if (this.surname.getText().isEmpty()){
                App.showAlert("Error", "Введите фамилию", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.name.getText().isEmpty()){
                App.showAlert("Error", "Введите имя", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.patronymic.getText().isEmpty()){
                App.showAlert("Error", "Введите отчество", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.additionalTextField.getText().isEmpty()){
                App.showAlert("Error", "Введите номер телефона", Alert.AlertType.INFORMATION);
                return;
            }
            Apprentice apprentice = new Apprentice();
            apprentice.setName(this.name.getText());
            apprentice.setSurname(this.surname.getText());
            apprentice.setPatronymic(this.patronymic.getText());
            apprentice.setPhoneNumber(this.additionalTextField.getText());
            try {
                ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
                apprenticeRepository.post(apprentice);
                App.closeWindow(actionEvent);
            } catch (IOException e) {
                App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            }
            catch (IllegalArgumentException e){
                App.showAlert("Error", "Ученик уже существует", Alert.AlertType.ERROR);
            }
        } else {
            if (this.surname.getText().isEmpty()){
                App.showAlert("Error", "Введите фамилию", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.name.getText().isEmpty()){
                App.showAlert("Error", "Введите имя", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.patronymic.getText().isEmpty()){
                App.showAlert("Error", "Введите отчество", Alert.AlertType.INFORMATION);
                return;
            }
            if (this.additionalTextField.getText().isEmpty()){
                App.showAlert("Error", "Введите опыт работы", Alert.AlertType.INFORMATION);
                return;
            }
            Trainer trainer = new Trainer();
            trainer.setName(this.name.getText());
            trainer.setSurname(this.surname.getText());
            trainer.setPatronymic(this.patronymic.getText());
            trainer.setExperience(Integer.parseInt(this.additionalTextField.getText()));
            try {
                TrainerRepository trainerRepository = new TrainerRepository();
                trainerRepository.post(trainer);
                App.closeWindow(actionEvent);
            } catch (IOException e) {
                App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
            }catch (IllegalArgumentException e){
                App.showAlert("Error", "Тренер уже существует", Alert.AlertType.ERROR);
            }

        }
    }
}
