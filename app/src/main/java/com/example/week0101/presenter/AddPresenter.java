package com.example.week0101.presenter;

import com.example.week0101.model.AddBean;
import com.example.week0101.model.HttpUtils;
import com.example.week0101.view.GouWActivity;
import com.example.week0101.view.IMainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class AddPresenter extends  BasePresenter<IMainView<AddBean>> {

    private final HttpUtils intants;

    public AddPresenter(GouWActivity gouWActivity){
        intants = HttpUtils.getIntants();
     }
     public  void AddData(String uid){
         Observable<AddBean> add = intants.api.Add(uid);
         add.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<AddBean>() {
                     @Override
                     public void accept(AddBean addBean) throws Exception {
                         getView().onSuccess(addBean);
                     }
                 });
     }
}
