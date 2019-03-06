package com.study.shy.mvp_demo.http.api;

import com.study.shy.mvp_demo.bean.AcgImg;
import com.study.shy.mvp_demo.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ServiceApi {

    @GET("/api/data/%E7%A6%8F%E5%88%A9/10/1")
    Observable<AcgImg> getAcgImg();
    @GET("/api/pictures/search/?key=%E6%8E%A8%E5%A5%B3%E9%83%8E&start=1&offset=2")
    Observable<TestBean> getAcgImg2();

}
