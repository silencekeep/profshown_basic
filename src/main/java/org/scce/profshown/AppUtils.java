package org.scce.profshown;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

public class AppUtils {
    /*@NotNull
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
    }*/
    //Public utilities
    public static Logger slf4jLogger = LoggerFactory.getLogger(AppUtils.class);
    public static ObjectMapper objectMapper = new ObjectMapper();
    //Public static functions
    public static String ReadAllText(String fileName) throws IOException {
        File f = new File(fileName);
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }
    public static void CreateConfigTemplate() throws IOException {
        File f = new File("config.json");
        FileOutputStream fs = new FileOutputStream(f);
        OutputStreamWriter sr = new OutputStreamWriter(fs);
        BufferedWriter br = new BufferedWriter(sr);
        br.append(objectMapper.writeValueAsString(InitialConfiguration.InitConfig));
        br.flush();
        br.close();;
    }
    public static String GetRandomBase64(){
        byte[] bytes = new byte[32];
        ThreadLocalRandom.current().nextBytes(bytes);
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
}
