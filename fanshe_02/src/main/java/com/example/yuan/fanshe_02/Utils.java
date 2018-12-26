package com.example.yuan.fanshe_02;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    private static Class cla;

    public static void setContext(Activity activity) {
        setcontentView(activity);
        setbindview(activity);
        setonclick(activity);
    }

    public static void setcontentView(Activity activity) {
         Class clazz = activity.getClass();
         ContentView annotation = (ContentView) clazz.getAnnotation(ContentView.class);
        int value = annotation.value();
        
        try {
            Method method = clazz.getMethod("setContentView", int.class);

            method.invoke(activity,value);
        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static void setbindview(Activity activity) {
        Class<? extends Activity> recfClass = activity.getClass();
        //获取类中的所有filed
        Field[] fields = recfClass.getDeclaredFields();
        //对获取到的field做便利
        for (Field field : fields)
        {
            //对便利到的field做判断 ,是否带特定注解标识
            if (field.isAnnotationPresent(BindView.class))
            {
                //获取到该filed的注解
                BindView bindView = field.getAnnotation(BindView.class);
                //获取到该filed的注解的value
                int id = bindView.value();
                //设置属性
                field.setAccessible(true);
                //对该field做控件绑定操作
                try {
                    field.set(activity,activity.findViewById(id));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setonclick(final Activity activity) {

        try {
            cla = Class.forName("com.example.yuan.fanshe_02.MainActivity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获取到里面的所有的方法
        Method[] methods = cla.getDeclaredMethods();
        //循环进行便利
        for (final Method method : methods)
        {
            OnClick onClick = method.getAnnotation(OnClick.class);
            if (onClick!=null)
            {
                int[] value = onClick.value();
                for (int i : value)
                {
                    if (i == -1) {
                        return;
                    }
                    View view = activity.findViewById(i);
                    if (view==null) {
                        return;
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                method.invoke(activity);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }


}
