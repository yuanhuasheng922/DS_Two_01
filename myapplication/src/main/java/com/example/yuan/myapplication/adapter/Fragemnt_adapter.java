package com.example.yuan.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yuan.myapplication.fragment.Pinglun;
import com.example.yuan.myapplication.fragment.Shop;
import com.example.yuan.myapplication.fragment.Xiangqing;

public class Fragemnt_adapter extends FragmentPagerAdapter {
    private String[] name=new String[]{"商品","详情","评论"};
;    public Fragemnt_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new Shop();
            case 1:
                return new Xiangqing();
            case 2:
                return new Pinglun();
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
}
