package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.User;
import com.semenbazanov.fencingschoolfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RegistrationController {
    public TextField login;
    public TextField password;
    public TextField name;

    public void toRegister(ActionEvent actionEvent) {
        if (this.login.getText().isEmpty()) {
            App.showAlert("Error", "Логин не заполнен", Alert.AlertType.ERROR);
            return;
        }
        if (this.password.getText().isEmpty()) {
            App.showAlert("Error", "Пароль не заполнен", Alert.AlertType.ERROR);
            return;
        }
        if (this.name.getText().isEmpty()) {
            App.showAlert("Error", "Имя не заполнено", Alert.AlertType.ERROR);
            return;
        }

        try {
            UserRepository userRepository = new UserRepository();
            User user = new User(this.login.getText(), this.password.getText(), this.name.getText());
            userRepository.post(user);
            App.openWindow("authorization.fxml", "authorization", null);
            App.closeWindow(actionEvent);
        } catch (IllegalArgumentException e) {
            App.showAlert("Error", "Пользователь существует", Alert.AlertType.ERROR);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toAuthorization(ActionEvent actionEvent) {
        try {
            App.openWindow("authorization.fxml", "Authorization", null);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
