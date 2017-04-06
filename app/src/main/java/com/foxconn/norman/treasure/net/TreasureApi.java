package com.foxconn.norman.treasure.net;


import com.foxconn.norman.treasure.treasure.Area;
import com.foxconn.norman.treasure.treasure.Treasure;
import com.foxconn.norman.treasure.treasure.detail.TreasureDetail;
import com.foxconn.norman.treasure.treasure.detail.TreasureDetailResult;
import com.foxconn.norman.treasure.user.User;
import com.foxconn.norman.treasure.user.login.LoginResult;
import com.foxconn.norman.treasure.user.register.RegisterResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gqq on 2017/3/30.
 */
// 对应服务器的接口
public interface TreasureApi {

    // 登录的请求
    @POST("/Handler/UserHandler.ashx?action=login")
    Call<LoginResult> login(@Body User user);

    // 注册的请求
    @POST("/Handler/UserHandler.ashx?action=register")
    Call<RegisterResult> register(@Body User user);

    // 获取区域内的宝藏数据
    @POST("/Handler/TreasureHandler.ashx?action=show")
    Call<List<Treasure>> getTreasureInArea(@Body Area area);

    //获取宝藏详情数据获取
    @POST("/Handler/TreasureHandler.ashx?action=tdetails")
    Call<List<TreasureDetailResult>> getTreasureDetail(@Body TreasureDetail treasureDetail);

}
