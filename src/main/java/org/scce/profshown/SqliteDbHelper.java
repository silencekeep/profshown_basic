package org.scce.profshown;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteDbHelper {
    public static void JustDoInitial() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + InitialConfiguration.InitConfig.getDatabaseFileName());
        Statement state = conn.createStatement();
        String sql = "DROP TABLE IF EXISTS \"table\";\n" +
                "CREATE TABLE \"table\" (\n" +
                "  \"id\" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"name\" TEXT,\n" +
                "  \"gender\" integer,\n" +
                "  \"email\" TEXT,\n" +
                "  \"islocal\" integer,\n" +
                "  \"avatar\" TEXT,\n" +
                "  \"title\" TEXT,\n" +
                "  \"office\" TEXT,\n" +
                "  \"research_direction\" TEXT,\n" +
                "  \"introduction\" TEXT\n" +
                ");\n";
        state.executeUpdate(sql);
        conn.close();
    }
    public static ArrayList<Professor> GetProfessorList() throws SQLException {
        ArrayList<Professor> list = new ArrayList<Professor>();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + InitialConfiguration.InitConfig.getDatabaseFileName());
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM [TABLE] ORDER BY id ASC";
        ResultSet results = statement.executeQuery(sql);
        while(results.next()){
            Professor prof = new Professor(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getInt("gender"),
                    results.getString("email"),
                    (results.getInt("islocal") == 0 ? false : true),
                    results.getString("avatar"),
                    results.getString("title"),
                    results.getString("office"),
                    results.getString("research_direction"),
                    results.getString("introduction"));
            list.add(prof);
        }
        return list;
    }
    public static ArrayList<ProfConcise> GetConciseList() throws SQLException {
        ArrayList<ProfConcise> list = new ArrayList<ProfConcise>();
        Connection conn = DriverManager.getConnection("jdbc:sqlite:" + InitialConfiguration.InitConfig.getDatabaseFileName());
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM [TABLE] ORDER BY id ASC";
        ResultSet results = statement.executeQuery(sql);
        while(results.next()){
            ProfConcise prof = new ProfConcise(
                    results.getInt("id"),
                    results.getString("name"),
                    (results.getInt("islocal") == 0 ? false : true),
                    results.getString("avatar"),
                    results.getString("title"),
                    results.getString("office"));
            list.add(prof);
        }
        return list;
    }
}
