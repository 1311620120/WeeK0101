package com.example.week0101.presenter;


public class BasePresenter <V> {
     private  V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

}
