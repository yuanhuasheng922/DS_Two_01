package com.example.yuan.myapplication.model;

import com.example.yuan.myapplication.callback.ICallBack;
import com.example.yuan.myapplication.callback.MyCallBack;
import com.example.yuan.myapplication.ok.OkHttp;

import java.util.Map;

public class IModelImple implements IModel {
    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params, final MyCallBack callBack) {
        OkHttp.getmInstance().postEnqueue(url, params, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                callBack.getRequest(obj);
            }

            @Override
            public void onfail(Exception e) {
callBack.getRequest(e);
            }
        });
    }
}
