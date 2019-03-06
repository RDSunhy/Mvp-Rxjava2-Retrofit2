package com.study.shy.mvp_demo.mvp.activity.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.study.shy.mvp_demo.R;
import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.bean.TestBean;
import com.study.shy.mvp_demo.mvp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivity extends BaseActivity<DemoView, DemoPresenter> implements DemoView {

    @BindView(R.id.bn_test)
    Button bnTest;

    @Override
    public int setLayoutId() {
        Log.e("流程","DemoActivity-->setLayoutId");
        return R.layout.activity_demo;
    }

    @Override
    public void initBundle(Bundle savedInstanceState) {
        Log.e("流程","initBundle");
    }

    @Override
    public void initView() {
        Log.e("流程","DemoActivity-->initView");
    }

    @Override
    public void initData() {
        Log.e("流程","DemoActivity-->initData");
    }

    @Override
    public DemoPresenter createPresenter() {
        Log.e("流程","DemoActivity-->createPresenter");
        return new DemoPresenter(this);
    }

    @Override
    public DemoView createView() {
        Log.e("流程","DemoActivity-->createView");
        return this;
    }

    //获取图片成功会调用
    @Override
    public void getImageResult(AcgImg bean) {
        Log.e("流程","DemoActivity-->getImageResult");
        Log.e("流程", "请求成功");
        Log.e("流程", "bean-->" + bean.toString());
        getView().dismisProgressDialog();
        Toast.makeText(getCurrentActivty(), "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String msg) {
        Log.e("流程","DemoActivity-->onFailed");
        getView().dismisProgressDialog();
        Toast.makeText(getCurrentActivty(), "" + msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.bn_test)
    public void onViewClicked() {
        Log.e("流程","DemoActivity-->onViewClicked");
        getPresenter().getImages();
    }
}
