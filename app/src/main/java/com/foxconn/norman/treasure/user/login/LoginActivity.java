package com.foxconn.norman.treasure.user.login;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.foxconn.norman.treasure.R;
import com.foxconn.norman.treasure.commons.RegexUtils;
import com.foxconn.norman.treasure.custom.AlertDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 登录页面
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_Username)
    EditText mEtUsername;
    @BindView(R.id.et_Password)
    EditText mEtPassword;
    @BindView(R.id.btn_Login)
    Button mBtnLogin;
    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        ButterKnife.bind(this);

        // toolbar
        // Toolbar作为ActionBar展示
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {

            // 设置返回的箭头,内部是选项菜单来处理的，而且Android内部已经给他设置好了id
            // android.R.id.home
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // 设置标题
            getSupportActionBar().setTitle(R.string.login);
        }

        // 设置文本变化的监听
        mEtUsername.addTextChangedListener(mTextWatcher);
        mEtPassword.addTextChangedListener(mTextWatcher);

    }

    // 文本变化的监听
    private TextWatcher mTextWatcher = new TextWatcher() {

        // 文本变化前
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        // 文本变化中
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        // 文本变化后
        @Override
        public void afterTextChanged(Editable s) {
            mUserName = mEtUsername.getText().toString();
            mPassword = mEtPassword.getText().toString();

            // 判断用户名和密码都不为空，按钮才可以点击
            boolean canLogin = !(TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword));
            mBtnLogin.setEnabled(canLogin);
        }
    };

    // 重写选项菜单的选中监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // 处理ActionBar上面的返回箭头的事件
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_Login)
    public void onClick() {

        // 账号不符合规则
        if (RegexUtils.verifyUsername(mUserName) != RegexUtils.VERIFY_SUCCESS) {

            // 弹个对话框提示
            AlertDialogFragment.getInstances(getString(R.string.username_error),
                    getString(R.string.username_rules))
                    .show(getSupportFragmentManager(),"usernameError");
            return;

        }

        // 密码不符合规范
        if (RegexUtils.verifyPassword(mPassword) != RegexUtils.VERIFY_SUCCESS) {
            // 弹个对话框

            AlertDialogFragment.getInstances(getString(R.string.password_error),
                    getString(R.string.password_rules))
                    .show(getSupportFragmentManager(),"passwordError");
            return;
        }

        // 要去做登录的业务逻辑,模拟用户登录的场景，异步任务来模拟

    }
}
