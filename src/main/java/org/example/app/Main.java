package org.example.app;

import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        try(Connection connection = Database.getConnection();
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("SELECT * FROM department")){

            while(resultSet.next()){
                map.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        for (Map.Entry<Integer, String> entry: map.entrySet()){
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ":" + value);
        }

    }
}