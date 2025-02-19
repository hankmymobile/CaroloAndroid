package com.gcarolo.loyalty;

import android.os.Bundle;

import com.gcarolo.loyalty.common.BaseActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.login.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        showLogin();
    }

    private void showLogin() {
        BaseFragment fragment = LoginFragment.newInstance();
        replaceFragment(fragment, fragment.getTagName());

    }
}