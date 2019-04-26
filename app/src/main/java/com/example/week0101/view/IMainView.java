package com.example.week0101.view;


public interface IMainView<T> {
     void  onSuccess(T t);
     void  onFails(String err);
}
