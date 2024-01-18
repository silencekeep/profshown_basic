package org.scce.profshown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InitialConfiguration {
    public static InitialConfiguration InitConfig;
    //jdbc:sqlite:/path/to/database.db
    @JsonProperty("hostName")
    private String hostName;
    @JsonProperty("port")
    private int port;
    @JsonProperty("DatabaseFileName")
    private String DatabaseFileName;
    @JsonProperty("IsOutputAccessRecord")
    private Boolean IsOutputAccessRecord;
    @JsonProperty("IsOutputLog")
    private Boolean IsOutputLog;
    @JsonProperty("OutputLogFileName")
    private String OutputLogFileName;
    @JsonProperty("AdminName")
    private String AdminName;
    @JsonProperty("AdminPassword")
    private String AdminPassword;

    public InitialConfiguration(){
        hostName = "0.0.0.0";
        port = 80;
        DatabaseFileName = "profshown.db";
        AdminName = "admin";
        AdminPassword = "123456";
    }
    public String getHostName(){
        return this.hostName;
    }
    public int getPort(){
        return this.port;
    }
    public String getDatabaseFileName(){
        return this.DatabaseFileName;
    }
    public String getAdminName(){
        return this.AdminName;
    }
    public String getAdminPassword(){
        return this.AdminPassword;
    }
}
