package com.example.chuckjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.DataInput;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Controller {
    public TableView<ChuckNorrisJoke> jokesTV;
    public TableColumn<ChuckNorrisJoke, String> jokesColumn;
    public TableColumn<ChuckNorrisJoke, String> dateColumn;
    public Button randomButton;
    public Button clearButton;
    public TextField searchTextField;
    public ImageView chuckImageView;

    @FXML
    public void initialize() throws Exception {
        Image chuckImg = new Image("https://api.chucknorris.io/img/avatar/chuck-norris.png");
        chuckImageView.setImage(chuckImg);
        jokesColumn.setCellValueFactory(new PropertyValueFactory<ChuckNorrisJoke, String>("theJoke"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ChuckNorrisJoke, String>("createdAt"));

        this.getRandomJoke();
    }

    public void getRandomJoke() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random"))
                .header("accept", "application/json")
                .header("X-RapidAPI-Key", "3bf16b1fe3msh992ef39833003cfp15de8ajsn4a96bd10a232")
                .header("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // reads JSON object's (key, value) pairs and store the values in my object's fields
        ObjectMapper objectMapper = new ObjectMapper();
        ChuckNorrisJoke joke = objectMapper.readValue(response.body(), ChuckNorrisJoke.class);
        jokesTV.getItems().add(joke);
    }

    public void searchJokes() throws Exception {
        String searchTerm = searchTextField.getText();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/search?query=" + searchTerm))
                .header("accept", "application/json")
                .header("X-RapidAPI-Key", "3bf16b1fe3msh992ef39833003cfp15de8ajsn4a96bd10a232")
                .header("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // Read JSON objects using JsonNode after readTree()
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.body());
        // By reading the JSON tree, the code can now get individual key:value pairs
        //
        // Now you can navigate through the JsonNode to access specific elements
        // For example, to access the "sport_title" of the first item in the array:
        JsonNode arrayOfJokes = jsonNode.get("result");
        for (JsonNode eachJoke : arrayOfJokes) {
            ChuckNorrisJoke newJoke = new ChuckNorrisJoke();
            newJoke.setCategories(objectMapper.convertValue(eachJoke.get("categories"), ArrayList.class));
            newJoke.setCreatedAt(eachJoke.get("created_at").asText());
            newJoke.setIconUrl(eachJoke.get("icon_url").asText());
            newJoke.setId(eachJoke.get("id").asText());
            newJoke.setUpdatedAt(eachJoke.get("updated_at").asText());
            newJoke.setUrl(eachJoke.get("url").asText());
            newJoke.setTheJoke(eachJoke.get("value").asText());
            jokesTV.getItems().add(newJoke);
        }
    }

    public void clearJokes() {
        jokesTV.getItems().clear();
    }
}