package com.foxconn.norman.treasure.net;


import com.foxconn.norman.treasure.user.User;
import com.foxconn.norman.treasure.user.login.LoginResult;
import com.foxconn.norman.treasure.user.register.RegisterResult;

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

}
