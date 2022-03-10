package com.example.igobrowsegame;

import java.io.Serializable;

public class room implements Serializable {
    private String RoomName;

    public room() {
    }

    public room(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getRoomName() {
        return RoomName;
    }
}
