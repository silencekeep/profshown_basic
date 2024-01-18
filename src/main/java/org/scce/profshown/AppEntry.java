package org.scce.profshown;

import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.core.JavalinConfig;
import io.javalin.core.util.JavalinLogger;
import io.javalin.jetty.JettyUtil;
import org.eclipse.jetty.util.log.Log;

import java.sql.SQLException;

public class AppEntry {
    public static void main(String[] args){
        //TestFunction
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Close DefaultLog created by framework
        Log.setLog(new JettyUtil.NoopLogger());
        JavalinLogger.enabled = false;

        //initialize javalin services
        Javalin app = Javalin.create();
        app.before(ApisController::before);
        app.get("/", ApisController::index);
        app.get("/test", ApisController::test);
        app.start(80);
        return;
    }
}
