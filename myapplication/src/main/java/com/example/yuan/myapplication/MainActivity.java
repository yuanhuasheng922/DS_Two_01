package com.example.yuan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.yuan.myapplication.adapter.UserAdapter;
import com.example.yuan.myapplication.api.Api;
import com.example.yuan.myapplication.bean.UserBean;
import com.example.yuan.myapplication.presenter.IPresenterIMple;
import com.example.yuan.myapplication.view.IView;
import com.example.yuan.myapplication.view.Main2Activity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IView {
    @BindView(R.id.image_back)
    ImageView image_back;
    @BindView(R.id.image_qiehuan)
    ImageView image_qiehuan;
    @BindView(R.id.edittext_input)
    EditText edittext_input;

    private IPresenterIMple presenterIMple;
    private boolean isShow=true;
    private int mPage;
    private UserAdapter userAdapter;
    private XRecyclerView xRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        presenterIMple = new IPresenterIMple(this);
        xRecyclerView = findViewById(R.id.xrecyclerview);
        if (isShow) {
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            xRecyclerView.setLayoutManager(layoutManager);
        }
        else
        {
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
            xRecyclerView.setLayoutManager(gridLayoutManager);
        }
        isShow=!isShow;
        mPage=1;
        userAdapter = new UserAdapter(isShow,this);
        xRecyclerView.setAdapter(userAdapter);

        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                getShow();
            }

            @Override
            public void onLoadMore() {
                getShow();
            }
        });

        userAdapter.setClick(new UserAdapter.Click() {
            @Override
            public void onSuccess(int position) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name",position+"");
                startActivity(intent);
            }
        });

        getShow();
    }

    @OnClick({R.id.image_back,R.id.image_qiehuan,R.id.edittext_input})
   public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.image_back:
                mPage=1;
                String s = edittext_input.getText().toString();
                Map<String,String> params=new HashMap<>();
                params.put("keywords",s);
                presenterIMple.getRequest(Api.TYPE_TITLE,UserBean.class,params);
                //Toast.makeText(this,"wowow",Toast.LENGTH_LONG).show();
                break;
            case R.id.image_qiehuan:
                initView();
                getShow();
                break;
            case R.id.edittext_input:

                break;
        }
    }
    private void getShow() {
        Map<String,String> params=new HashMap<>();
        params.put("keywords","手机");
        params.put("page",mPage + "");
        presenterIMple.getRequest(Api.TYPE_TITLE,UserBean.class,params);
    }

    @Override
    public void getRequest(Object data) {

        UserBean userBean= (UserBean) data;
        if (mPage==1)
        {
            userAdapter.setmDatas(userBean.getData());
        }
        else
        {
            userAdapter.addmDatas(userBean.getData());
        }
        mPage++;
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();
    }
}
