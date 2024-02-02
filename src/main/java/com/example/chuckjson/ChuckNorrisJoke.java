package com.example.chuckjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChuckNorrisJoke {
    @JsonProperty("categories")
    ArrayList<String> categories;

    @JsonProperty("created_at")
    Date createdAt;

    @JsonProperty("icon_url")
    String iconUrl;

    @JsonProperty("id")
    String id;

    @JsonProperty("updated_at")
    Date updatedAt;

    @JsonProperty("url")
    String url;

    @JsonProperty("value")
    String theJoke;

    public ChuckNorrisJoke() {
    }

    public ChuckNorrisJoke(ArrayList<String> categories, Date created_at, String iconUrl, String id, Date updatedAt, String url, String value) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String createdAtString) throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        this.createdAt = dateFormatter.parse(createdAtString);
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public void setUpdatedAt(String updatedAtString) throws Exception {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        this.updatedAt = dateFormatter.parse(updatedAtString);
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
