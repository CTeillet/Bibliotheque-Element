package com.teillet.bibliothequeElement.database;

import com.teillet.bibliothequeElement.interfaces.database.IBDElements;
import com.teillet.bibliothequeElement.interfaces.library.IElements;
import com.teillet.bibliothequeElement.library.Elements;
import com.teillet.bibliothequeElement.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.teillet.bibliothequeElement.utils.Factory;

import java.lang.reflect.Constructor;
import java.sql.*;

public class BDElements implements IBDElements {
    static final Factory factory = Factory.getFact();

    public static boolean addElements(@NotNull IElements elements){
        String req = "INSERT INTO elements(path,title, description, type) VALUES(?, ?,'{}', ?);";
        return executeUpdate(elements, req);
    }

    public static boolean removeElements(IElements elements) {
        String req = "Delete from elements where path=? and title=?;";
        return executeUpdate(elements, req);
    }

    private static boolean executeUpdate(IElements elements, String req) {
        try (Connection conn = BDConnect.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(req)){
            preparedStatement.setString(1, elements.getPath());
            preparedStatement.setString(2, elements.getTitle());
            preparedStatement.setString(3, elements.getType());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int size() {
        String req = "Select Count(*) from elements;";
        try (Connection conn = BDConnect.connect();
             Statement statement = conn.createStatement()){
            ResultSet res = statement.executeQuery(req);
            return res.getInt("total");
        } catch (SQLException e) {
            return -1;
        }
    }

    public static void removeAllElements() {
        @SuppressWarnings("SqlWithoutWhere") String req = "Delete from elements;";
        try (Connection conn = BDConnect.connect();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(req);
        } catch (SQLException ignored) {
        }

    }

    public static IElements getElementByTitle(String title) {
        String req = "Select * from elements where title = ?;";
        return getElements(title, req);
    }

    public static IElements getElementByPath(String path) {
        String req = "Select * from elements where path = ?;";
        return getElements(path, req);
    }

    @Nullable
    private static IElements getElements(String elem, String req) {
        try (Connection conn = BDConnect.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(req)){
            preparedStatement.setString(1, elem);
            preparedStatement.execute();
            ResultSet res = preparedStatement.getResultSet();
            return Utils.Object2Elements(res.getString("type"),
                    res.getString("path"),
                    res.getString("title"));
        } catch (SQLException e) {
            return null;
        }
    }

    public static boolean containsElements(IElements elements) {
        String req = "Select Count(*) from elements where title=? and path=?";
        try (Connection conn = BDConnect.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(req)){
            preparedStatement.setString(1, elements.getTitle());
            preparedStatement.setString(2, elements.getPath());
            preparedStatement.execute();
            ResultSet res = preparedStatement.getResultSet();
            return res.getInt("total")>0;
        } catch (SQLException e) {
            return false;
        }
    }
}
