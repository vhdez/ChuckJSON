package com.example.chuckjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChuckNorrisJoke {
    @JsonProperty("categories")
    ArrayList<String> categories;

    @JsonProperty("created_at")
    LocalDate createdAt;

    @JsonProperty("icon_url")
    String iconUrl;

    @JsonProperty("id")
    String id;

    @JsonProperty("updated_at")
    LocalDate updatedAt;

    @JsonProperty("url")
    String url;

    @JsonProperty("value")
    String theJoke;

    public ChuckNorrisJoke() {
    }

    public ChuckNorrisJoke(ArrayList<String> categories, LocalDate created_at, String iconUrl, String id, LocalDate updatedAt, String url, String value) {
        this.categories = categories;
        this.createdAt = created_at;
        this.iconUrl = iconUrl;
        this.id = id;
        this.updatedAt = updatedAt;
        this.url = url;
        this.theJoke = value;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String createdAtString) throws Exception {
        String timeWithoutHours = createdAtString.substring(0, createdAtString.indexOf(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        createdAt = LocalDate.parse(timeWithoutHours, formatter);
    }
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setUpdatedAt(String updatedAtString) throws Exception {
        String timeWithoutHours = updatedAtString.substring(0, updatedAtString.indexOf(" "));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        updatedAt = LocalDate.parse(timeWithoutHours, formatter);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTheJoke() {
        return theJoke;
    }

    public void setTheJoke(String theJoke) {
        this.theJoke = theJoke;
    }
}
