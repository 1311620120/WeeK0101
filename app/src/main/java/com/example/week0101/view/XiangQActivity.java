package com.example.week0101.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week0101.R;
import com.example.week0101.model.XiangBean;
import com.example.week0101.presenter.XiangPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class XiangQActivity extends AppCompatActivity implements  IMainView{
  private  String pid;
    private SimpleDraweeView img;
    private TextView name;
    private TextView price;
    private XiangPresenter xiangPresenter;
    private SharedPreferences sp;
   boolean isCheck;
    private XiangBean xiangBean;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_q);
        EventBus.getDefault().register(this);
        initView();
        initData();

    }
    private void initView() {
        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        button = findViewById(R.id.button);
    }
    private void initData() {
        xiangPresenter = new XiangPresenter(this);
        xiangPresenter.Xiang(pid);
        xiangPresenter.setView(this);
        sp = getSharedPreferences("add", MODE_PRIVATE);
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String pid = xiangBean.getData().getPid();
               String id = sp.getString("pid", "");
               String[] split = id.split(",");
               if (id.equals("")){
                   Toast.makeText(XiangQActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                   SharedPreferences.Editor edit = sp.edit();
                   edit.putString("pid",id+","+pid);
                   edit.commit();
               }else {
                   for (int i=0;i<split.length;i++){
                       if (pid.equals(split[i])){
                           Toast.makeText(XiangQActivity.this, "已加入", Toast.LENGTH_SHORT).show();
                           isCheck=true;
                       }
                   }
                   if (!isCheck){
                       SharedPreferences.Editor edit = sp.edit();
                       edit.putString("pid",id+","+pid);
                       edit.commit();
                   }
               }

           }
       });

    }



    @Subscribe(threadMode =ThreadMode.MAIN,sticky = true)
    public  void EventBus(String id){
        pid=id;
    }

    @Override
    public void onSuccess(Object o) {
        xiangBean = (XiangBean)o;
        String images = xiangBean.getData().getImages();
        img.setImageURI(images);
        String title = xiangBean.getData().getTitle();
        name.setText(title);
        int price1 = xiangBean.getData().getPrice();
        price.setText(price1+"");


    }

    @Override
    public void onFails(String err) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
