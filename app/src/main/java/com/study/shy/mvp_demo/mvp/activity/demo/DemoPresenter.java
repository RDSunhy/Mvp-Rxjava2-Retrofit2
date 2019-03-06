package com.study.shy.mvp_demo.mvp.activity.demo;

import android.content.Context;
import android.util.Log;

import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.bean.TestBean;
import com.study.shy.mvp_demo.http.HttpObserver;
import com.study.shy.mvp_demo.http.callback.OnResultCallBack;
import com.study.shy.mvp_demo.mvp.base.presenter.MvpBasePresenter;

public class DemoPresenter extends MvpBasePresenter<DemoView> {

    private DemoModel mDemoModel;
    private HttpObserver<AcgImg> httpObserver;
    private HttpObserver<TestBean> httpObserver2;
    public DemoPresenter(Context context) {
        super(context);
        mDemoModel = new DemoModel();
    }

    /**
     * 网络请求 获取登录结果
     *
     */
    public void getImages() {
        /**
         * 显示进度条
         */
        Log.e("流程","DemoPresenter-->getImages");

        getView().showProgressDialog();
        /**
         * 初始化观察者
         */
        if (null == httpObserver) {
            Log.e("流程","DemoPresenter-->if初始化观察者");
            httpObserver = new HttpObserver<>(new OnResultCallBack<AcgImg>() {

                @Override
                public void onSuccess(AcgImg bean) {
                    Log.e("流程","DemoPresenter-->OnResultCallBack-->onSuccess");
                    getView().getImageResult(bean);
                }

                @Override
                public void onError(String errorMsg) {
                    Log.e("流程","DemoPresenter-->OnResultCallBack-->onError");
                    getView().onFailed(errorMsg);
                }

            });
        }
        /**
         * 调用请求的方法
         */
        mDemoModel.getImages(httpObserver);
    }

    @Override
    public void dettachView() {
        super.dettachView();
        //反注册
        if (null != httpObserver) {
            httpObserver.unSubscribe();
            httpObserver = null;
        }
    }
}
