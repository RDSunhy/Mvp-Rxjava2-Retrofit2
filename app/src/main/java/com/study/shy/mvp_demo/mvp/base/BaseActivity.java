package com.study.shy.mvp_demo.mvp.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.study.shy.mvp_demo.R;
import com.study.shy.mvp_demo.mvp.LoadingDialog;
import com.study.shy.mvp_demo.mvp.base.presenter.MvpPresenter;
import com.study.shy.mvp_demo.mvp.base.view.MvpBaseView;
import com.study.shy.mvp_demo.mvp.base.view.MvpView;

import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpBaseView {

    private P presenter;
    private V view;
    public String TAG = "";
    LoadingDialog loadingDialog;
    //当前Activity的实例
    static private BaseActivity currentActivity;

    public P getPresenter() {
        return presenter;
    }

    public V getView() {
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = setLayoutId();
        setContentView(layoutResID);
        //黄油刀
        ButterKnife.bind(this);
        //绑定 presenter
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        //view
        if (this.view == null) {
            this.view = createView();
        }
        //attach
        if (this.presenter != null && this.view != null) {
            this.presenter.attachView(view);
        }
        //current activity
        currentActivity = this;
        //tag
        TAG = currentActivity.getClass().getSimpleName() + "_";
        //初始化各种数据
        initBundle(savedInstanceState);
        initView();
        initData();
    }

    //设置布局id
    public abstract int setLayoutId();

    //初始化bundle
    public abstract void initBundle(Bundle savedInstanceState);

    //初始化View
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    /**
     * 绑定P层
     *
     * @return
     */
    public abstract P createPresenter();

    /**
     * 创建View层
     *
     * @return
     */
    public abstract V createView();

    @Override
    protected void onResume() {
        super.onResume();
        //current activity  为了解决progress  dialog 弹出 context改变的问题
        currentActivity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.dettachView();
            this.presenter = null;
        }
        dismisProgressDialog();
    }

    /**
     * 获取当前Activity的实例
     *
     * @return
     */
    public static Activity getCurrentActivty() {
        return currentActivity;
    }

    @Override
    public void showProgressDialog() {
        loadingDialog = new LoadingDialog(currentActivity,R.style.ConfigDialog);
        loadingDialog.show();
    }

    @Override
    public void dismisProgressDialog() {
        if (null != loadingDialog){
            loadingDialog.dismiss();
        }
    }
}
