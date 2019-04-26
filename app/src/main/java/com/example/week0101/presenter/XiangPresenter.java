package com.example.week0101.presenter;

import com.example.week0101.model.HttpUtils;
import com.example.week0101.model.XiangBean;
import com.example.week0101.view.IMainView;
import com.example.week0101.view.XiangQActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class XiangPresenter extends  BasePresenter<IMainView<XiangBean>> {

    private final HttpUtils intants;

    public  XiangPresenter(XiangQActivity xiangQActivity){
        intants = HttpUtils.getIntants();
     }
        public  void  Xiang(String pid){
            Observable<XiangBean> xiang = intants.api.Xiang(pid);
            xiang.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<XiangBean>() {
                        @Override
                        public void accept(XiangBean xiangBean) throws Exception {
                            getView().onSuccess(xiangBean);
                        }
                    });
        }
}
