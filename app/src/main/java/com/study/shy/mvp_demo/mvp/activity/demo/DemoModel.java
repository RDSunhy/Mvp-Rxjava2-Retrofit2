package com.study.shy.mvp_demo.mvp.activity.demo;

import android.util.Log;

import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.bean.TestBean;
import com.study.shy.mvp_demo.http.HttpManager;
import com.study.shy.mvp_demo.http.api.ServiceApi;
import com.study.shy.mvp_demo.http.callback.ApiServiceMethodCallBack;
import com.study.shy.mvp_demo.mvp.base.model.MvpBaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class DemoModel extends MvpBaseModel {

    /**
     * 获取请求图片
     * 参数可以自行设置
     * 此处get请求无需传参
     */
    public void getImages(Observer<AcgImg> observer) {
        Log.e("流程","DemoModel-->getImages");
        HttpManager.getInstance("http://gank.io/").HttpManagerRequest(observer, new ApiServiceMethodCallBack<AcgImg>() {
            @Override
            public Observable<AcgImg> createObservable(ServiceApi serviceApi) {
                Log.e("流程","DemoModel-->createObservable");
                return serviceApi.getAcgImg();
            }
        });
    }

}
