package org.scce.profshown;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Professor {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("gender")
    private char gender;
    @JsonProperty("email")
    private String email;
    @JsonProperty("islocal")
    private Boolean islocal;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("title")
    private String title;
    @JsonProperty("office")
    private String office;
    @JsonProperty("research")
    private String research;
    @JsonProperty("introduction")
    private String introduction;
    public Professor(){

    }
    public Professor(int id,String name,int gender,String email,Boolean islocal,String avatar,String title,String office,String research,String introduction){
        this.id = id;
        this.name = name;
        this.gender = gender != 1 ? (gender == 0 ? 'F' : 'U') : 'M';
        this.email = email;
        this.islocal = islocal;
        this.avatar = avatar;
        this.title = title;
        this.office = office;
        this.research = research;
        this.introduction = introduction;
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
    public char getGender(){
        return this.gender;
    }
    public void setGender(char gender){
        this.gender = gender;
    }
    public void setGenderInt(int gender){
        this.gender = gender != 1 ? (gender == 0 ? 'F' : 'U') : 'M';
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(){
        this.email = email;
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
    public String getResearch(){
        return this.research;
    }
    public void setResearch(){
        this.research = research;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }

}
