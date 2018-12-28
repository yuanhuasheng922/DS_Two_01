package com.example.yuan.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuan.myapplication.R;
import com.example.yuan.myapplication.api.Api;
import com.example.yuan.myapplication.bean.ImageBean;
import com.example.yuan.myapplication.bean.Message;
import com.example.yuan.myapplication.bean.User;
import com.example.yuan.myapplication.bean.UserBean;
import com.example.yuan.myapplication.presenter.IPresenterIMple;
import com.example.yuan.myapplication.view.IView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Shop extends Fragment implements IView {
//    @BindView(R.id.pinglun)
//    Button button;
//    @BindView(R.id.xiangqing)
//    Button xiangqing;
       private Banner banner;
    private String name;
    private IPresenterIMple presenterIMple;
    private TextView price;
    private TextView title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.shop_item,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(getActivity());
                Fresco.initialize(getActivity());
        banner = view.findViewById(R.id.banner);
        presenterIMple = new IPresenterIMple(this);
        price = view.findViewById(R.id.price);
        title = view.findViewById(R.id.title);

        TextView pinglun=view.findViewById(R.id.pinglun);
        TextView xiangqing=view.findViewById(R.id.xiangqing);

        xiangqing.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Double a = Double.valueOf(price.getText().toString());
               ImageBean.DataBean dataBean=new ImageBean.DataBean();
               dataBean.setPrice(a);
               EventBus.getDefault().postSticky(dataBean);
           }
       });
    pinglun.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                            String s = title.getText().toString();
                ImageBean.DataBean bean=new ImageBean.DataBean();

                bean.setTitle(s);

                EventBus.getDefault().postSticky(bean);
        }
    });


        Intent intent =getActivity().getIntent();

        name = intent.getStringExtra("name");

        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Uri uri = Uri.parse((String) path);
                imageView.setImageURI(uri);
            }

            @Override
            public ImageView createImageView(Context context) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
                return simpleDraweeView;
            }
        });
        loadData();
    }
//    @OnClick({R.id.xiangqing,R.id.pinglun})
//    public void onClick(View view)
//    {
//        switch (view.getId())
//        {
//            case R.id.xiangqing:
//
//                break;
//            case R.id.pinglun:

//                break;
//        }
    //}

       private void loadData() {
        Map<String, String> params = new HashMap<>();
        params.put("pid", name);
        presenterIMple.getRequest(Api.TYPE_IMAGE, ImageBean.class, params);
   }

       @Override
    public void getRequest(Object data) {
        if (data instanceof ImageBean) {
            ImageBean detailBean = (ImageBean) data;
            List<String> list = new ArrayList<>();
            String[] split = detailBean.getData().getImages().split("\\|");
            for (int i = 0; i < split.length; i++) {
                list.add(split[i]);
            }
            banner.setImages(list);
            banner.start();
            price.setText(detailBean.getData().getPrice() +"");
            price.setTextColor(Color.RED);
            title.setText(detailBean.getData().getTitle());

        }
    }
}
