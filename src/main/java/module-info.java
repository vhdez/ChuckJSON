module com.example.chuckjson {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.chuckjson to javafx.fxml;
    exports com.example.chuckjson;
}