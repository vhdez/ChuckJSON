package com.example.chuckjson;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChuckNorrisJoke {
    @JsonProperty("categories")
    String categories[];

    @JsonProperty("created_at")
    String createdAt;

    @JsonProperty("icon_url")
    String iconUrl;

    @JsonProperty("id")
    String id;

    @JsonProperty("updated_at")
    String updatedAt;

    @JsonProperty("url")
    String url;

    @JsonProperty("value")
    String theJoke;

    public ChuckNorrisJoke() {
    }

    public ChuckNorrisJoke(String[] categories, String created_at, String iconUrl, String id, String updatedAt, String url, String value) {
        this.categories = categories;
        this.createdAt = created_at;
        this.iconUrl = iconUrl;
        this.id = id;
        this.updatedAt = updatedAt;
        this.url = url;
        this.theJoke = value;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
