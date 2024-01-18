package org.scce.profshown;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ApisController {
    public static void index(@NotNull Context context){
        LocalDateTime date = LocalDateTime.now();
        context.html(String.format("<html><head><title>SCCE of USTB profshown bgApp</title></head><body><h1 style=\"color:red\">Server Time: %s</h1</body></html>",date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))));
        return;
    }
    //
    public static void before(@NotNull Context context) {

        context.header("Server","USTB-SCCE");
    }
    public static void test(@NotNull Context context){
        String result = AppUtils.MySqlTest(context.queryParam("name"));
        context.html("<html>" +
                "<head>" +
                "<meta charset=\"utf-8\"/>" +
                "<title>View in Table:test</title>" +
                "</head>" +
                "<body>" +
                "<h3>Table: test</h3>" +
                result +
                "</body>" +
                "</html>");
        return;
    }
}
