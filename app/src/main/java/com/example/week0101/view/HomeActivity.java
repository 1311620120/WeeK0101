package com.example.week0101.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.week0101.R;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomTabBar ButtonBar = findViewById(R.id.buttomBar);
     ButtonBar.init(getSupportFragmentManager())
             .setImgSize(50,50)
             .setFontSize(9)
             .setTabPadding(4,6,10)
             .addTabItem("首页",R.mipmap.ic_launcher_round,MainActivity.class)
             .addTabItem("购物车",R.mipmap.ic_launcher_round,GouWActivity.class);
    }
}
