package com.example.chuckjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class Controller {
    public TableView<ChuckNorrisJoke> jokesTV;
    public TableColumn<ChuckNorrisJoke, String> jokesColumn;
    public TableColumn<ChuckNorrisJoke, String> dateColumn;
    public Button randomButton;
    public TextField searchTextField;

    @FXML
    public void initialize() throws Exception {
        jokesColumn.setCellValueFactory(new PropertyValueFactory<ChuckNorrisJoke, String>("theJoke"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ChuckNorrisJoke, String>("createdAt"));
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
        System.out.println(joke.getTheJoke());
    }

    public void searchJokes() throws Exception {
        String searchText = "gum";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/search?query=" + searchText))
                .header("accept", "application/json")
                .header("X-RapidAPI-Key", "3bf16b1fe3msh992ef39833003cfp15de8ajsn4a96bd10a232")
                .header("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        ObjectMapper objectMapper = new ObjectMapper();

        List<ChuckNorrisJoke> jokes = Arrays.asList(objectMapper.readValue(response.body(), ChuckNorrisJoke[].class));
        System.out.println(jokes);
    }

}