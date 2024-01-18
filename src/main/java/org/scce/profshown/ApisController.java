package org.scce.profshown;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApisController {
    public static void index(@NotNull Context context){
        context.status(401);
        return;
    }
    public static void images(@NotNull Context context){
        String fileName = context.pathParam("imageName");
        File f = new File("./local_images/"+fileName);
        if (f.exists()) {
            FileInputStream fin = null;
            try {
                fin = new FileInputStream(f);
            } catch (Exception e) {
                context.result(String.format("Internal Server Error have occurred. Message:%s"
                ,e.getMessage()));
                context.status(500);
                return;
            }
            context.result(fin);
            context.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            context.contentType("application/octet-stream");
            context.status(200);
        } else {
            context.result("Not found");
            context.status(404);
        }
    }
    public static void detail(@NotNull Context context) {
        try {
            int id = Integer.parseInt(context.pathParam("id"));
            ArrayList<Professor> list = SqliteDbHelper.GetProfessorList();
            Professor target = null;
            for(Professor item : list){
                if(id == item.getId()){
                    target = item;
                    break;
                }
            }
            String s = new String(AppUtils.objectMapper.writeValueAsString(target));
            if(target == null) throw new Exception("Not found");
            context.result(s.getBytes(StandardCharsets.UTF_8));
            context.contentType("application/json; charset=utf-8");
            context.status(200);
        } catch (JsonProcessingException | SQLException e) {
            context.result(String.format("Internal Server Error have occurred. Message:%s"
                    ,e.getMessage()));
            context.status(500);
            return;
        } catch(Exception e){
            context.result(String.format("%s",e.getMessage()));
            context.status(404);
            return;
        }
    }
    public static void conciseList(@NotNull Context context){
        try {
            ArrayList<ProfConcise> list = SqliteDbHelper.GetConciseList();
            String s = new String(AppUtils.objectMapper.writeValueAsString(list));
            String json = String.format("{\"length\":%d,%s}",list.size(),s);
            context.result(json.getBytes(StandardCharsets.UTF_8));
            context.contentType("application/json; charset=utf-8");
            context.status(200);
        } catch (Exception e) {
            context.result(String.format("Internal Server Error have occurred. Message:%s"
                    ,e.getMessage()));
            context.status(500);
            return;
        }
    }
    public static void before(@NotNull Context context) {
        context.header("Server","USTB-SCCE");
    }
}
