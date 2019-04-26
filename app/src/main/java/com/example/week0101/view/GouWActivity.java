package com.example.week0101.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.week0101.R;
import com.example.week0101.model.SelectBean;
import com.example.week0101.presenter.SelectPresenter;

import java.util.List;

public class GouWActivity extends Fragment implements  IMainView{

    private View view;
    private ExpandableListView expand;
    private CheckBox checkAll;
    private TextView priceAll;
    private List<SelectBean.DataBean> data;
    private SelectAdapter selectAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_gou_w, null);
        initData();
        return view;
    }

    private void initData() {
        expand = view.findViewById(R.id.expand);
        SelectPresenter selectPresenter = new SelectPresenter(this);
        selectPresenter.SelectData();
        selectPresenter.setView(this);
        checkAll = view.findViewById(R.id.checkAll);
        priceAll = view.findViewById(R.id.priceAll);

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (checkAll.isChecked()){
                    setCheckAll(true);
                   }else {
                       setCheckAll(false);
                   }
                    setMoenyAll();
                selectAdapter.notifyDataSetChanged();
            }

        });

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof  SelectBean){
            SelectBean selectBean =(SelectBean)o;
            data = selectBean.getData();
            Log.e("sss", ""+ data);
            selectAdapter = new SelectAdapter(getActivity(), data);
             expand.setAdapter(selectAdapter);
            for (int i = 0; i < data.size() ; i++) {
                expand.expandGroup(i);
            }
        }
    }
    public  void setCheckAll(Boolean check){
        for (int i = 0; i <data.size() ; i++) {
            SelectBean.DataBean dataBean = data.get(i);
            dataBean.isCheck(check);
            List<SelectBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j <list.size() ; j++) {
                SelectBean.DataBean.ListBean listBean = list.get(j);
                listBean.setCheck(check);
            }

        }
        selectAdapter.notifyDataSetChanged();
    }
    public void  setMoenyAll(){
        double money=0;
        for (int i = 0; i <data.size() ; i++) {
            for (int j = 0; j <data.get(i).getList().size() ; j++){
                if (data.get(i).getList().get(j).isCheck(checked) == true) {
                    double num = data.get(i).getList().get(j).getNum() * data.get(i).getList().get(j).getPrice();
                    money+=num;
                }
            }
            priceAll.setText("$:"+money);
        }

    }

    @Override
    public void onFails(String err) {

    }
}
