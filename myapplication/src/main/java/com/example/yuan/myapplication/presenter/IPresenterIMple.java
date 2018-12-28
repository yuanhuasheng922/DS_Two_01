package com.example.yuan.myapplication.presenter;

import com.example.yuan.myapplication.callback.MyCallBack;
import com.example.yuan.myapplication.model.IModelImple;
import com.example.yuan.myapplication.presenter.IPresenter;
import com.example.yuan.myapplication.view.IView;

import java.util.Map;

public class IPresenterIMple implements IPresenter {

    private IView mIView;
    private IModelImple mIModelImple;

    public IPresenterIMple(IView mIView) {
        this.mIView = mIView;
        mIModelImple=new IModelImple();
    }

    @Override
    public void getRequest(String url, Class clazz, Map<String, String> params) {
        mIModelImple.getRequest(url, clazz, params, new MyCallBack() {
            @Override
            public void getRequest(Object data) {
                mIView.getRequest(data);
            }
        });
    }
}
