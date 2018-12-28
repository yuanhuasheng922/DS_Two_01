package com.example.yuan.myapplication.model;

import com.example.yuan.myapplication.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void getRequest(String url, Class clazz, Map<String,String> params, MyCallBack callBack);
}
