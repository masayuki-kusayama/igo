package com.example.igobrowsegame;

public class test {
    public static void main(String[] args) throws Exception {
        test t = new test();
        t.dbtest();
    }

    public void dbtest() {
        try {
            manipulateroomDB.create("roomfore-tan");
            System.out.println(manipulateroomDB.exist("roomfore-tan"));
            manipulateroomDB.delete("roomfore-tan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
