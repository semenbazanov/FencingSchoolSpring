package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.User;
import com.semenbazanov.fencingschoolfx.retrofit.UserRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AuthorizationController {
    public TextField login;
    public TextField password;

    public void toEnter(ActionEvent actionEvent) {
        if (this.login.getText().isEmpty()) {
            App.showAlert("Error", "Введите логин", Alert.AlertType.ERROR);
            return;
        }
        if (this.password.getText().isEmpty()) {
            App.showAlert("Error", "Введите пароль", Alert.AlertType.ERROR);
            return;
        }
        try {
            UserRepository userRepository = new UserRepository();
            User user = userRepository.get(this.login.getText(), this.password.getText());
            if (user != null) {
                Preferences preferences = Preferences.userRoot();
                preferences.putLong("id", user.getId());
                App.openWindow("main.fxml", "main", user);
                App.closeWindow(actionEvent);
            } else {
                App.openWindow("registration.fxml", "registration", null);
            }
        } catch (IOException e) {
            App.showAlert("Error", "Пользователь не существует", Alert.AlertType.ERROR);
        }
    }

    public void toRegister(ActionEvent actionEvent) {
        try {
            App.openWindow("registration.fxml", "registration", null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
