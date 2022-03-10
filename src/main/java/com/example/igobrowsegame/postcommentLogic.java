package com.example.igobrowsegame;

import java.util.List;

public class postcommentLogic {
    public void post(comment com, List<comment> comlist) {
        comlist.add(0,com);
    }
}
