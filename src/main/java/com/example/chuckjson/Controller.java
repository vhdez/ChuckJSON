package com.example.chuckjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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
    public Button randomButton1;
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
        String jsonJoke = this.getJSONfromURL("https://api.chucknorris.io/jokes/random");
        System.out.println("JOKE: " + jsonJoke);

        // read 1 JSON object (its "key":"value" pairs) into the fields of a ChuckNorrisJoke object.
        ObjectMapper objectMapper = new ObjectMapper();
        ChuckNorrisJoke joke = objectMapper.readValue(jsonJoke, ChuckNorrisJoke.class);
        jokesTV.getItems().add(joke);
    }

    public void searchJokes() throws Exception {
        String searchTerm = searchTextField.getText();
        String jsonJokes = this.getJSONfromURL("https://api.chucknorris.io/jokes/search?query=" + searchTerm);
        System.out.println("JOKES: " + jsonJokes);

        // Read JSON objects using JsonNode after readTree()
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonJokes);
        // By reading the JSON tree, the code can now get individual "key":"value" pairs
        // The value of the "result" key is an ARRAY of JSON objects
        JsonNode arrayOfJokes = jsonNode.get("result");
        for (JsonNode eachJoke : arrayOfJokes) {
            // read 1 JSON object (its "key":"value" pairs) into the fields of a ChuckNorrisJoke object.
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

    String getJSONfromURL(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }

    public void clearJokes() {
        jokesTV.getItems().clear();
    }
}