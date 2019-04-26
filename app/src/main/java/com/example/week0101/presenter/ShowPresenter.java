package com.example.week0101.presenter;

import com.example.week0101.model.HttpUtils;
import com.example.week0101.model.ShowBean;
import com.example.week0101.view.IMainView;
import com.example.week0101.view.MainActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class ShowPresenter extends  BasePresenter <IMainView<ShowBean>> {

    private final HttpUtils intants;

    public ShowPresenter(MainActivity mainActivity){
        intants = HttpUtils.getIntants();
     }
     public  void ShowData(){
         Observable<ShowBean> show = intants.api.Show();
         show.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<ShowBean>() {
                     @Override
                     public void accept(ShowBean showBean) throws Exception {
                           getView().onSuccess(showBean);
                     }
                 });
     }
}
