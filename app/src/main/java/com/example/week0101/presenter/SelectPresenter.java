package com.example.week0101.presenter;


import com.example.week0101.model.HttpUtils;
import com.example.week0101.model.SelectBean;
import com.example.week0101.view.GouWActivity;
import com.example.week0101.view.IMainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SelectPresenter extends  BasePresenter<IMainView<SelectBean>> {

    private final HttpUtils intants;

    public SelectPresenter(GouWActivity gouWActivity){
        intants = HttpUtils.getIntants();
     }
     public  void SelectData(){
         Observable<SelectBean> add = intants.api.select();
         add.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<SelectBean>() {
                     @Override
                     public void accept(SelectBean selectBean) throws Exception {
                         getView().onSuccess(selectBean);
                     }
                 });
     }
}
