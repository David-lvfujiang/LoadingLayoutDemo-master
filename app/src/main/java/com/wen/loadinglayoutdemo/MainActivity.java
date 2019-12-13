package com.wen.loadinglayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.weavey.loading.lib.LoadingLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
            @Override
            public void onReload(View v) {

                Toast.makeText(MainActivity.this,"重新载入",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.status_suc, R.id.status_err, R.id.status_load, R.id.status_empty, R.id.status_wifi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //载入成功
            case R.id.status_suc:
                loadingLayout.setVisibility(View.GONE);
                break;
            //加载失败
            case R.id.status_err:
                loadingLayout.setStatus(LoadingLayout.Error);
                break;
            //正在加载
            case R.id.status_load:
                loadingLayout.setVisibility(View.VISIBLE);
                loadingLayout.setStatus(LoadingLayout.Loading);
                break;
            //没有数据
            case R.id.status_empty:
                loadingLayout.setStatus(LoadingLayout.Empty);
                break;
            //没有网络
            case R.id.status_wifi:
                loadingLayout.setStatus(LoadingLayout.No_Network);
                break;
        }
    }
}
