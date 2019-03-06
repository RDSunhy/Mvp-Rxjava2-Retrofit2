package com.study.shy.mvp_demo.http;

import android.util.Log;

import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.http.api.ServiceApi;
import com.study.shy.mvp_demo.http.callback.ApiServiceMethodCallBack;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
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
        Log.e("流程","HttpManager获取实例");
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

        Log.e("流程","HttpManager构造器");

        /**
         * 初始化 okhttp
         */
/*        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                //.addInterceptor(new CommonInterceptor())
                .build();*/
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
//                .client(okHttpClient)
                .build();
        mServiceApi = mRetrofit.create(ServiceApi.class);
    }

    /**
     * 普通的网络请求注册
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<T> o, Observer<T> s) {
        Log.e("流程","HttpManager-->toSubscribe");

        o
                /**文件操作，网络操作等 子线程**/
                .subscribeOn(Schedulers.io())
                /**map 观察者按照提供的方法执行**/
                .map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        return t;
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
     * @param <T>
     * @param subscriber 观察者
     * @param callBack   回调
     */
    public <T> void HttpManagerRequest(Observer<T> subscriber, ApiServiceMethodCallBack<T> callBack) {
        try {
            Log.e("流程","HttpManager-->HttpManagerRequest");
            toSubscribe(callBack.createObservable(mServiceApi), subscriber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
