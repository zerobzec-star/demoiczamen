package com.example.demo4;


import java.sql.*;

public class DBHandler {
    Connection connection;
    public void DBCoonnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user= "postgres";
        String password = "123";
        connection= DriverManager.getConnection(url,user,password);
    }
    public ResultSet qxecuteQuery(String query) throws SQLException {
        if (connection==null||connection.isClosed()){
            System.out.println("");
            return null;
        }else {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            return  rs;
        }
    }
    public void qxecuteUpdate(String query) throws SQLException {
        connection.prepareStatement(query).executeUpdate();
    }
}
