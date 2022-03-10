package com.example.igobrowsegame;

import java.sql.*;

public class manipulateroomDB {
    public static void create(String s) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
        String sql = "insert into rooms values (?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, s);
        psmt.executeUpdate();
    }

    public static boolean exist(String s) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
        String sql = "select * from rooms where room = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, s);
        ResultSet result = psmt.executeQuery();
        return result.next();
    }

    public static void delete(String s) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
        String sql = "delete from rooms where room = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, s);
        psmt.executeUpdate();
    }
}

