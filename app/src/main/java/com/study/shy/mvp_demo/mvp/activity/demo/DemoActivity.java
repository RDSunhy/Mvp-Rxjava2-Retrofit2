package com.study.shy.mvp_demo.mvp.activity.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.study.shy.mvp_demo.R;
import com.study.shy.mvp_demo.mvp.base.BaseActivity;

public class DemoActivity extends BaseActivity<DemoView,DemoPresenter> implements DemoView{

    @Override
    public int setLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public DemoPresenter createPresenter() {
        return new DemoPresenter(this);
    }

    @Override
    public DemoView createView() {
        return this;
    }
}
