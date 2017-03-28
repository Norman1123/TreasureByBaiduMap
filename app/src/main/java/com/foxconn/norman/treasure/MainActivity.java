package com.foxconn.norman.treasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.foxconn.norman.treasure.commons.ActivityUtils;
import com.foxconn.norman.treasure.user.login.LoginActivity;
import com.foxconn.norman.treasure.user.register.RegisterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    private ActivityUtils mActivityUtils;
    private Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityUtils = new ActivityUtils(this);
        mUnbinder = ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_Register, R.id.btn_Login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Register:
                mActivityUtils.startActivity(RegisterActivity.class);
                break;
            case R.id.btn_Login:
                mActivityUtils.startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
