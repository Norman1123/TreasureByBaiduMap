package com.foxconn.norman.treasure.user.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import com.foxconn.norman.treasure.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


// 注册页面
public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.et_Confirm)
    EditText mEtConfirm;
    @BindView(R.id.btn_Register)
    Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);

        // toolbar展示
        setSupportActionBar(mToolbar);
        if (getSupportActionBar()!=null){

            // 显示返回箭头
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // 显示标题
            getSupportActionBar().setTitle(R.string.register);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            // 处理Actionbar上返回的箭头
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_Register)
    public void onClick() {
    }
}
