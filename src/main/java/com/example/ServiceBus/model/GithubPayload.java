package com.example.ServiceBus.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubPayload {
    private String name;
    private String before;
    private String after;
    private String action;
    private String id;

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonCreator
    public GithubPayload(
            @JsonProperty("name") String name,
            @JsonProperty("before") String before,
            @JsonProperty("after") String after,
            @JsonProperty("action") String action,
            @JsonProperty("id") String id) {
        this.name = name;
        this.before = before;
        this.after = after;
        this.action = action;
        this.id = id;
    }

    public GithubPayload() {
    }
}

