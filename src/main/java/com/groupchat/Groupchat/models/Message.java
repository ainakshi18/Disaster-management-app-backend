package com.groupchat.Groupchat.models;

import java.time.LocalDateTime;

public class Message {

    private String name;
    private String content;
    private LocalDateTime timestamp;

    public Message(String name, String content) {
        this.name = name;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
