package com.example.yuan.myapplication.bean;

public class Message {

    private Object object;
    private String id;

    public Message(Object object, String id) {
        this.object = object;
        this.id = id;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
