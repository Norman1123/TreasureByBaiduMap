package com.foxconn.norman.treasure;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.foxconn.norman.treasure.user.UserPrefs;


/**
 * Created by gqq on 2017/3/31.
 */

public class TreasureApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // 用户仓库的初始化
        UserPrefs.init(getApplicationContext());
        // 百度地图SDK的初始化
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

    }
}
