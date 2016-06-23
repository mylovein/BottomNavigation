package com.example.bottomnavigation.item;

/**
 * Created by Administrator on 2016/5/10.
 */
public class BottomNavigationItem {
//    private int id;
    private String title;
    private int icon;
    private int color;

    public BottomNavigationItem() {
    }

    public BottomNavigationItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public BottomNavigationItem(int icon, String title, int color) {
        this.title = title;
        this.icon = icon;
        this.color = color;
    }


//    public BottomNavigationItem(int id, String title, int icon) {
//        this.id = id;
//        this.title = title;
//        this.icon = icon;
//    }
//
//    public BottomNavigationItem(int id, String title, int icon, int color) {
//        this.id = id;
//        this.title = title;
//        this.icon = icon;
//        this.color = color;
//    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
