package com.example.notandi.hanastel.domain;

/**
 * Created by Hrafnkell on 30/10/2015.
 */
public class Cocktail {
    private int _id;
    private String Name;
    private String Picture;
    private String Description;

    public Cocktail(){}

    public Cocktail(int _id, String name, String picture, String description) {
        this._id = _id;
        Name = name;
        Picture = picture;
        Description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
