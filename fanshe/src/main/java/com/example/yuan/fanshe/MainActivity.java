package com.example.yuan.fanshe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getName()   获取完整类名
        //  getSimpleName()  获取简单类名
        //  getDeclaredFields()   获取类中所有属性
        //  getDeclaredMethods()   获取类中所有方法
        //  getDeclaredConstructors()  获取类中所有构造器
        //  newInstance()          实例化类的对象（公开的无参构造）

        try {
            //加载class对象
            Class clazz = Class.forName("com.example.yuan.fanshe.Student");
            //2.获取所有共有构造方法
            Log.i("dj","===========所有公有构造的方法============");
            //getConstructors() 是取到全部的构造方法
            Constructor[] conArry = clazz.getConstructors();
            for (Constructor c : conArry)
            {
                Log.i("dj","公有" +c+"");
            }

            Log.i("dj","===========所有所有的构造的方法(包括:私有,受保护的,默认,共有)============");
            //  getDeclaredMethods()   获取类中所有方法
            conArry = clazz.getDeclaredConstructors();
            for (Constructor c : conArry)
            {
                Log.i("dj","所有的构造方法(包括:私有,受保护的,默认,共有)" +c+"");
            }
            Log.i("dj","===========获取共有,无参的构造方法============");
            //因为是无参的构造方法 所以类型是一个null 这里需要的事一个参数的类型,切记是类型
            //2.返回的事描述这个无参构造函数的类对象
            Constructor con = clazz.getConstructor(null);
            Log.i("dj","共有无参" +con+"");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
