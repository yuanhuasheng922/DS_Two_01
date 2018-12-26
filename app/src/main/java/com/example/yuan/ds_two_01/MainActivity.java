package com.example.yuan.ds_two_01;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;
    private String url ="http://img5.imgtn.bdimg.com/it/u=604482600,1393215599&fm=26&gp=0.jpg";

    private int iterations=10;
    private int blurRadius=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleDraweeView = findViewById(R.id.simpleDraweeView);
//        Uri uri = Uri.parse("http://img5.imgtn.bdimg.com/it/u=604482600,1393215599&fm=26&gp=0.jpg");
//        simpleDraweeView.setImageURI(uri);


        showUrlBlur(simpleDraweeView,url,iterations,blurRadius);
        ViewGroup.LayoutParams params = simpleDraweeView.getLayoutParams();

    }

    private void showUrlBlur(SimpleDraweeView simpleDraweeView,String url,int iterations,int blurRadius) {
        {
            try {
                Uri uri = Uri.parse(url);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setOldController(simpleDraweeView.getController())
                        .setImageRequest(request)
                        .build();
                simpleDraweeView.setController(controller);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
