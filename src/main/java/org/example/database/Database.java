package org.example.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class Database {

    private static Connection connection = null;


    public static Connection getConnection(){
        if(connection == null){
            try {
                Properties properties = loadProperties();
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
            }
            catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
        return connection;

    }
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            }
            catch(SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void closeStatement(Statement statement){
        if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            }
            catch(SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }


    private static Properties loadProperties(){
        try (FileInputStream fs = new FileInputStream("src/main/resources/application.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        }
        catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }



}
