package com.example.dailytask;

public class ListItem {
    private String title;
    private String description;

    public ListItem(String _tilte, String _description)
    {
        this.title = _tilte;
        this.description = _description;
    }

    public  String getTitle(){
        return  title;
    }

    public  String getDesctiption(){
        return  description;
    }
}