package com.study.shy.mvp_demo.http.callback;

/**
 * 网络请求的返回(成功或失败)
 */
public interface OnResultCallBack<T> {
    //成功的返回
    void onSuccess(T t);
    //失败的返回
    void onError(String errorMsg);
}
