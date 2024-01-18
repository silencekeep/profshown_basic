package org.scce.profshown;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.javalin.Javalin;
import io.javalin.core.util.JavalinLogger;
import io.javalin.jetty.JettyUtil;
import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class AppEntry {
    public static Logger slf4jLogger = LoggerFactory.getLogger(AppEntry.class);
    public static void main(String[] args) throws JsonProcessingException {
        //Loading SQLite JDBC Driver
        try {
            slf4jLogger.info("Loading SQLite JDBC Driver.");
            Class.forName("org.sqlite.JDBC").newInstance();
            slf4jLogger.info("Loading SQLite JDBC Driver successfully.");
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            slf4jLogger.error("Loading SQLite JDBC Driver failed, Message:{}",e.getMessage(),e);
            slf4jLogger.info("Application will be terminated.");
            return;
        }
        //Load Configuration
        try {
            slf4jLogger.info("Loading configuration.");
            String json = AppUtils.ReadAllText("./config.json");
            InitialConfiguration.InitConfig = AppUtils.objectMapper.readValue(json,InitialConfiguration.class);
            slf4jLogger.info("Successfully loaded configuration.");
        }
        catch (IOException e) {
            slf4jLogger.info("Cannot find config.json at folder './', decided to create an configuration template.");
            InitialConfiguration.InitConfig = new InitialConfiguration();
            try {
                AppUtils.CreateConfigTemplate();
                slf4jLogger.info("Create config.json template successfully, you can use it directly or change it.");
            } catch (IOException ex) {
                slf4jLogger.error("Create configuration template failed, Message:{}",e.getMessage(),e);
                slf4jLogger.info("Application will be terminated.");
                return;
            }
        }

        //Check Database have already existed or not
        try {
            File f = new File(InitialConfiguration.InitConfig.getDatabaseFileName());
            if(!f.exists()) throw new FileNotFoundException("Database file not found");
            if(f.length() == 0) {
                f.delete();
                throw new FileNotFoundException("Database is Empty.");
            }
        }
        catch (FileNotFoundException e) {
            slf4jLogger.warn("Recreating new database in the path you have set, Message:{}",e.getMessage());
            try {
                SqliteDbHelper.JustDoInitial();
            } catch (SQLException ex) {
                slf4jLogger.error("Create new database failed, Message:{}",ex.getMessage(),ex);
                slf4jLogger.info("Application will be terminated.");
                return;
            }
        }

        //Lookup images directory/folder
        File dir = new File("./local_images");
        if(!dir.exists()) if(!dir.mkdir()) try {
            throw new FileNotFoundException("Directory not exist but create failed.");
        }
        catch (FileNotFoundException e) {
            slf4jLogger.error("Process failed in creating new directory of local images, Message:{}",e.getMessage(),e);
            slf4jLogger.info("Application will be terminated.");
            return;
        }

        //Close DefaultLog created by jetty and javalin
        Log.setLog(new JettyUtil.NoopLogger());
        JavalinLogger.enabled = false;

        //initialize javalin services
        slf4jLogger.info("Initialize web services.");
        SvcRunnable svcRunnable = new SvcRunnable();
        Thread thread = new Thread(svcRunnable);
        thread.start();
        slf4jLogger.info("Successfully initialized web service.");
        //ManagerSessionRunnable mgrRunnable = new ManagerSessionRunnable();
        //Thread scnThr = new Thread(mgrRunnable);
        //scnThr.start();
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                slf4jLogger.info("Loop has been interrupted, quit.");
                return;
            }
        }
    }
}
