package com.example.igobrowsegame;

import java.io.Serializable;

public class comment implements Serializable {
    private String comment;
    private String name;

    public comment() {
    }

    public comment(String name, String comment) {
        this.comment = comment;
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public String getcomment() {
        return comment;
    }
}
