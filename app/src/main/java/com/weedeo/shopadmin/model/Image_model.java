package com.weedeo.shopadmin.model;

import android.graphics.Bitmap;

public class Image_model {




    /**
     * id : 23970
     * name : servo.jpg
     * path : /storage/emulated/0/Arqivos CNC/servo.jpg
     */

    private int id;
    private String name;
    private Bitmap path;

    public Image_model(Bitmap bitmap, String s) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPath() {
        return path;
    }

    public void setPath(Bitmap path) {
        this.path = path;
    }
}
