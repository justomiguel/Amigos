package com.amigos.util;

/**
 * Created by sati on 28/09/2014.
 */
public class ItemDrawer {

    private String title;
    private int image;

    public ItemDrawer(String title, int image) {
        this.title = title;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }
}
