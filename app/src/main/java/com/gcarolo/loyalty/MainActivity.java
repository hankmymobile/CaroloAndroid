package com.gcarolo.loyalty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.gcarolo.loyalty.common.BaseActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.balance.BalanceFragment;
import com.gcarolo.loyalty.modules.favorites.MisFavoritosFragment;
import com.gcarolo.loyalty.modules.login.LoginFragment;
import com.gcarolo.loyalty.modules.notification.NotificationFragment;
import com.gcarolo.loyalty.modules.restaurants.RestaurantsFragment;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    BottomNavigationView bottomNav = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.balance);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new BalanceFragment()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNav.setVisibility(View.VISIBLE);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        // By using switch we can easily get
        // the selected fragment
        // by using there id.
        Fragment selectedFragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.balance) {
            selectedFragment = new BalanceFragment();
        } else if (itemId == R.id.favorites) {
            selectedFragment = new MisFavoritosFragment();
        } else if (itemId == R.id.notification) {
            selectedFragment = new NotificationFragment();
        } else if (itemId == R.id.restaurants) {
            selectedFragment = new RestaurantsFragment();
        }
        // It will help to replace the
        // one fragment to other.
        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
        }
        return true;
    };

    private void showSaldo() {
        BaseFragment fragment = WelcomePageFragment.newInstance();
        replaceFragment(fragment, fragment.getTagName());
    }

    public BottomNavigationView getBottomNav (){
        return bottomNav;
    }
}