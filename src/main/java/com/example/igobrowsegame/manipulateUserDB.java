package com.example.igobrowsegame;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class manipulateUserDB {
    public static void put(String s, String p) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
        // SHA-512（SHA-2）
        MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        byte[] sha512_result = sha512.digest(p.getBytes());
        String sharesult = String.format("%040x", new BigInteger(1, sha512_result));
        String sql = "insert into users values (?,?)";
        System.out.println(sql);
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, s);
        psmt.setString(2, sharesult);
        psmt.executeUpdate();

    }

    public static boolean exist(String s) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
        String sql = "select * from users where id = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1, s);
        ResultSet result = psmt.executeQuery();
        return result.next();
    }

    public static boolean authentication(String name, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {
        if (exist(name)) {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfoforigo?useSSL=false", "masayuki", "KMvd7xrwucvd7xrwuc?");
            String sql = "select password from users where id = ?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, name);
            ResultSet result = psmt.executeQuery();
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            byte[] sha512_result = sha512.digest(password.getBytes());
            String sharesult = String.format("%040x", new BigInteger(1, sha512_result));
            result.next();
            return (sharesult.equals(result.getString("password")));

        }
        return false;
    }


}
