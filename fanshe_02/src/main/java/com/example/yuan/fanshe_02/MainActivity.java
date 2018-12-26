package com.example.yuan.fanshe_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textview)
    TextView textView;
    @BindView(R.id.textview2)
    TextView textView2;
    @BindView(R.id.textview3)
    TextView textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setContext(MainActivity.this);

    }
    @OnClick(R.id.textview)
    public void text()
    {
        Toast.makeText(MainActivity.this,"11",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.textview2)
    public void text2()
    {
        Toast.makeText(MainActivity.this,"22",Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.textview3)
    public void text3()
    {
        Toast.makeText(MainActivity.this,"33",Toast.LENGTH_SHORT).show();
    }
}
