package com.ycm.topic;

import lombok.Data;

@Data
public class Topic {

    private String id;
    private String name;
    private String description;

    public Topic(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}