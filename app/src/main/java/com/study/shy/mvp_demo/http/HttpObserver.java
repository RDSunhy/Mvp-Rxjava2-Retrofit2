package com.study.shy.mvp_demo.http;

import com.study.shy.mvp_demo.http.callback.OnResultCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//Http观察者类

public class HttpObserver<T> implements Observer<T> {

    private OnResultCallBack mOnResultListener;

    //网络请求监听(成功/失败)
    public HttpObserver(OnResultCallBack listener){
        this.mOnResultListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

}
