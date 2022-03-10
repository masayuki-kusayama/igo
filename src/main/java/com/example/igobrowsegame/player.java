package com.example.igobrowsegame;

public class player {
    private String kuro;
    private String shiro;

    public void set(String role, String name) {
        switch (role) {
            case "kuro":
                this.kuro = name;
                break;
            case "shiro":
                this.shiro = name;
                break;
            default:
        }
    }

    public String get(String role) {
        switch (role) {
            case "kuro":
                return this.kuro;
            case "shiro":
                return this.shiro;
            default:
                return null;
        }
    }

    public String getopponent(String role) {
        switch (role) {
            case "kuro":
                return this.shiro;
            case "shiro":
                return this.kuro;
            default:
                return null;
        }
    }

}
