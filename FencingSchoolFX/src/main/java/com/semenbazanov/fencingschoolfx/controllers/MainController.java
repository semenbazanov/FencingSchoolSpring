package com.semenbazanov.fencingschoolfx.controllers;

import com.semenbazanov.fencingschoolfx.App;
import com.semenbazanov.fencingschoolfx.model.Apprentice;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import com.semenbazanov.fencingschoolfx.model.User;
import com.semenbazanov.fencingschoolfx.retrofit.ApprenticeRepository;
import com.semenbazanov.fencingschoolfx.retrofit.TrainerRepository;
import com.semenbazanov.fencingschoolfx.retrofit.UserRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

public class MainController implements ControllerData<User> {
    public Label label;
    public ListView<Apprentice> apprenticesList;
    public ListView<Trainer> trainersList;
    private User user;

    @Override
    public void initData(User value) {
        this.user = value;
        this.label.setText(this.label.getText() + user.getName());

        try {
            this.initListView();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }

        this.apprenticesList.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Apprentice apprentice = this.apprenticesList.getSelectionModel().getSelectedItem();
                try {
                    App.openWindowAndWait("apprentice.fxml", "apprentice", apprentice);
                    this.initListView();
                } catch (IOException e) {
                    App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });

        this.trainersList.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Trainer trainer = trainersList.getSelectionModel().getSelectedItem();
                try {
                    App.openWindowAndWait("trainer.fxml", "trainer", trainer);
                    this.initListView();
                } catch (IOException e) {
                    App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    public void initListView() throws IOException {
        ApprenticeRepository apprenticeRepository = new ApprenticeRepository();
        List<Apprentice> apprentices = apprenticeRepository.get();
        this.apprenticesList.setItems(FXCollections.observableArrayList(apprentices));

        TrainerRepository trainerRepository = new TrainerRepository();
        List<Trainer> trainers = trainerRepository.get();
        this.trainersList.setItems(FXCollections.observableArrayList(trainers));
    }

    public void toDeleteUser(ActionEvent actionEvent) {
        try {
            UserRepository userRepository = new UserRepository();
            userRepository.delete(this.user.getId());
            App.openWindow("authorization.fxml", "authorization", null);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void toExit(ActionEvent actionEvent) {
        try {
            App.openWindow("authorization.fxml", "Authorization", null);
            App.closeWindow(actionEvent);
            Preferences preferences = Preferences.userRoot();
            preferences.remove("id");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void toAdd(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("add.fxml", "add", null);
            this.initListView();
        } catch (IOException e) {
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
