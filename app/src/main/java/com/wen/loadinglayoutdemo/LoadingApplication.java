package com.wen.loadinglayoutdemo;

import android.app.Application;

import com.weavey.loading.lib.LoadingLayout;


public class LoadingApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadingLayout.getConfig()
                .setLoadingPageLayout(R.layout.loading)
                //下面的设置项，组件都提供了默认值，不想更改的可以省略
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.mipmap.notdata)
                .setEmptyImage(R.mipmap.eror)
                .setNoNetworkImage(R.mipmap.notnetwork)
                .setAllTipTextColor(R.color.colorGray)
                .setAllTipTextSize(18)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(18)
                .setReloadButtonTextColor(R.color.colorGray)
                .setReloadButtonWidthAndHeight(150,40);
    }
}
