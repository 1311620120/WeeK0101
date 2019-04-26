package com.example.week0101.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

 public interface Api {
      @GET("home/getHome")
    Observable <ShowBean> Show();
      @GET("product/getProductDetail")
    Observable <XiangBean> Xiang(@Query("pid")String pid);

    @GET("product/addCart")
    Observable <AddBean> Add(@Query("uid") String uid);

    @GET("product/getCarts?uid=51")
    Observable <SelectBean> select();
}
