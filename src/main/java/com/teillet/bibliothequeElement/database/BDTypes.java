package com.teillet.bibliothequeElement.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDTypes {
    public static List<String> getTypes(){
        String req = "Select type from types;";
        try (Connection conn = BDConnect.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(req)){
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            List<String> l = new ArrayList<>();
            while(rs.next()){
                String s = rs.getString("type").toUpperCase();
                l.add(s);
            }
            rs.close();
            return l;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
