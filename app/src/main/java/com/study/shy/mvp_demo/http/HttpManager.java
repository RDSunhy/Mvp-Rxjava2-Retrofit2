package com.study.shy.mvp_demo.http;

import android.util.Log;

import com.study.shy.mvp_demo.http.api.CommonResult;
import com.study.shy.mvp_demo.http.api.ServiceApi;
import com.study.shy.mvp_demo.http.callback.ApiServiceMethodCallBack;
import com.study.shy.mvp_demo.http.excpetion.ApiException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    //实例
    private static volatile HttpManager instancce = null;
    //retrofit
    private Retrofit mRetrofit = null;
    //api service
    private ServiceApi mServiceApi = null;
    /**
     * 获取实例的方法
     * @return 返回当前实例
     */
    public static HttpManager getInstance(String url) {
        if (null == instancce) {
            synchronized (HttpManager.class) {
                if (null == instancce) {
                    instancce = new HttpManager(url);
                }
            }
        }
        return instancce;
    }

    public HttpManager(String url) {
        /**
         * 初始化 retrofit
         */
        mRetrofit = new Retrofit.Builder()
                //base url
                .baseUrl(url)
                //gosn 转换器
                .addConverterFactory(GsonConverterFactory.create())
                //rxjava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mServiceApi = mRetrofit.create(ServiceApi.class);
    }

    /**
     * 普通的网络请求注册
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<CommonResult<T>> o, Observer<T> s) {
                o
                /**文件操作，网络操作等 子线程**/
                .subscribeOn(Schedulers.io())
                /**map 观察者按照提供的方法执行**/
                .map(new Function<CommonResult<T>, T>() {
                    @Override
                    public T apply(@NonNull CommonResult<T> t) {
                        Log.e("返回结果--->", t.toString());
                        if (t.isSuccess()) {//请求成功的返回
                            if (null == t.getResult()) {
                                //返回数据为null的情况
                                throw new ApiException(t.getError().getMessage());
                            }
                            return t.getResult();
                        } else {//请求失败
                            throw new ApiException(t.getError().getMessage());
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                /**回到主线程**/
                .observeOn(AndroidSchedulers.mainThread())
                /**订阅**/
                .subscribe(s);
    }

    /**
     * 公共的外部调用请求的方法
     * @param subscriber 观察者
     * @param callBack   回调
     * @param <T>
     */
    public <T> void HttpManagerRequest(Observer<CommonResult<T>> subscriber, ApiServiceMethodCallBack<CommonResult<T>> callBack) {
        try {
            toSubscribe(callBack.createObservable(mServiceApi), subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
