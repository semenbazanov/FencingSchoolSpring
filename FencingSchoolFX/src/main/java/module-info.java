module com.semenbazanov.fencingschoolfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires retrofit2;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires okhttp3;
    requires retrofit2.converter.jackson;


    opens com.semenbazanov.fencingschoolfx to javafx.fxml;
    exports com.semenbazanov.fencingschoolfx;
    exports com.semenbazanov.fencingschoolfx.controllers;
    opens com.semenbazanov.fencingschoolfx.controllers to javafx.fxml;
    exports com.semenbazanov.fencingschoolfx.dto to com.fasterxml.jackson.databind;
    exports com.semenbazanov.fencingschoolfx.model to com.fasterxml.jackson.databind;
    opens com.semenbazanov.fencingschoolfx.model to javafx.base;
}