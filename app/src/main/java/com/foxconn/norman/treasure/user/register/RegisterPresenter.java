package com.foxconn.norman.treasure.user.register;

import android.os.AsyncTask;

/**
 * Created by gqq on 2017/3/28.
 */
// 注册的业务类
public class RegisterPresenter {

    private RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView) {
        mRegisterView = registerView;
    }

    // 注册的业务实现
    public void register(){
        new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // 进度条展示
                mRegisterView.showProgress();
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // 拿到数据处理UI
                // 隐藏进度、显示信息、跳转页面
                mRegisterView.hideProgress();
                mRegisterView.showMessage("注册成功");
                mRegisterView.navigateToHome();

            }
        }.execute();
    }
}
