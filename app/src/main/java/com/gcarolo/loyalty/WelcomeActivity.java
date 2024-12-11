package com.gcarolo.loyalty;

import android.os.Bundle;

import com.gcarolo.loyalty.common.BaseActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        showSaldo();
    }

    private void showSaldo() {
        BaseFragment fragment = WelcomePageFragment.newInstance();
        replaceFragment(fragment, fragment.getTagName());
    }
}