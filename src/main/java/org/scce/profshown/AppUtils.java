package org.scce.profshown;

import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class AppUtils {
    @NotNull
    public static String MySqlTest(String name) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://120.53.84.25/scce_profshown","root","806Developer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement stat = null;
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String ss = "";
        if(name != null && !name.isEmpty())
            ss = " where name = '" + name + "'";
        ResultSet rs = null;
        try {
            rs = stat.executeQuery(String.format("select * from test%s;",ss));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<table><thead><tr><th>id</th><th>name</th><th>title</th><th>avatar</th><th>introduction</th></tr></thead><tbody>");
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String s = null;
            try {
                s = String.format("<tr>" +
                                "<td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>" +
                                "</tr>",rs.getString("id")
                        ,rs.getString("name")
                        ,rs.getString("title")
                        ,rs.getString("avatar")
                        ,rs.getString("introduction"));
                System.out.println(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sb.append(s+"\r\n");
        }
        sb.append("</tbody></table>");
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
