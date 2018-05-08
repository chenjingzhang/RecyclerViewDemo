package com.dxt2.recyclerview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CircleActivity extends AppCompatActivity {
    CircleImageView imageView;
    RoundedImageView roundedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        roundedImageView = (RoundedImageView) findViewById(R.id.round);
        imageView = (CircleImageView) findViewById(R.id.img);
        Picasso.with(this).load("http://gd1.alicdn.com/bao/uploaded/i1/2816618896/TB2_31fnFXXXXahXXXXXXXXXXXX_!!2816618896.jpg").into(imageView);
        //http://gd1.alicdn.com/bao/uploaded/i1/2816618896/TB2_31fnFXXXXahXXXXXXXXXXXX_!!2816618896.jpg
        //http://img3.imgtn.bdimg.com/it/u=2489051743,1646771720&fm=21&gp=0.jpg
        Picasso.with(this).load("http://img3.imgtn.bdimg.com/it/u=2489051743,1646771720&fm=21&gp=0.jpg").into(roundedImageView);


    }
}
