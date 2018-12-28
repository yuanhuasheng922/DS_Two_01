package com.example.yuan.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuan.myapplication.R;
import com.example.yuan.myapplication.adapter.Fragemnt_adapter;
import com.example.yuan.myapplication.api.Api;
import com.example.yuan.myapplication.bean.ImageBean;
import com.example.yuan.myapplication.presenter.IPresenterIMple;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity  {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tabLayout = findViewById(R.id.table);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new Fragemnt_adapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);



    }









}
