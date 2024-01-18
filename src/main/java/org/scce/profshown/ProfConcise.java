package org.scce.profshown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfConcise {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("islocal")
    private Boolean islocal;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("title")
    private String title;
    @JsonProperty("office")
    private String office;
    public ProfConcise(){

    }
    public ProfConcise(int id,String name,Boolean islocal,String avatar,String title,String office){
        this.id = id;
        this.name = name;
        this.islocal = islocal;
        this.avatar = avatar;
        this.title = title;
        this.office = office;
    }

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Boolean avatarIsLocal(){
        return this.islocal;
    }
    public String getAvatar(){
        return this.avatar;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getOffice(){
        return this.office;
    }
    public void setOffice(String office){
        this.office = office;
    }
}
