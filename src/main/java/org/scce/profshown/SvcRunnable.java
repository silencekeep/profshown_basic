package org.scce.profshown;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SvcRunnable implements Runnable{
    public static Logger slf4jLogger = LoggerFactory.getLogger(SvcRunnable.class);
    @Override
    public void run() {
        Javalin app = Javalin.create();

        //Register routes
        registerRoutes(app);
        slf4jLogger.info("Register routes complete.");
        app.start(InitialConfiguration.InitConfig.getHostName(),InitialConfiguration.InitConfig.getPort());
    }
    public static void registerRoutes(Javalin app){
        //Others.
        app.before(ApisController::before);
        app.get("/", ApisController::index);

        //Exposed API for WeChat client.
        app.get("/faculty/image/{imageName}",ApisController::images);
        app.get("/faculty/list",ApisController::conciseList);
        app.get("/faculty/detail/{id}",ApisController::detail);
    }
}
