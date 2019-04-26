package com.example.week0101.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week0101.R;
import com.example.week0101.model.ShowBean;
import com.example.week0101.presenter.ShowPresenter;

public class MainActivity extends Fragment implements  IMainView{

    private RecyclerView recycler;
    private ShowPresenter showPresenter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
        initData();
        return view;
    }

    private void initData() {
        recycler = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        showPresenter = new ShowPresenter(this);
        showPresenter.ShowData();
        showPresenter.setView(this);
    }

    @Override
    public void onSuccess(Object o) {
        ShowBean showBean =(ShowBean)o;
        MyAdapter myAdapter = new MyAdapter(getActivity(), showBean);
        recycler.setAdapter(myAdapter);
    }

    @Override
    public void onFails(String err) {

    }
}
