package me.agape.example.propties;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCProperties {
    public static Statement statement() throws ClassNotFoundException {
        String url = "jdbc:mariadb://97.64.111.135:3306/xiuxian";
        String name = "root";
        String password = "chh741398387";
        String Driver = "org.mariadb.jdbc.Driver";
        Class.forName(Driver);
        Connection connection;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(url,name,password);
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;


    }}
