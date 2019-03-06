package com.study.shy.mvp_demo.http;

import android.util.Log;

import com.study.shy.mvp_demo.http.callback.OnResultCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//Http观察者类

public class HttpObserver<T> implements Observer<T> {

    private OnResultCallBack mOnResultListener;
    private Disposable mDisposable;

    //网络请求监听(成功/失败)
    public HttpObserver(OnResultCallBack listener){
        this.mOnResultListener = listener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (mOnResultListener != null) {
            mOnResultListener.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e("流程","HttpObserver-->onError");
        Log.e("错误信息",""+e.getMessage());
        mOnResultListener.onError(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public void unSubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

}
