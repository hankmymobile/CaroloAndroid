package com.gcarolo.loyalty;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.gcarolo.loyalty.common.BaseActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.modules.balance.BalanceFragment;
import com.gcarolo.loyalty.modules.favorites.MisFavoritosFragment;
import com.gcarolo.loyalty.modules.gerente.GerenteFragment;
import com.gcarolo.loyalty.modules.notification.NotificationFragment;
import com.gcarolo.loyalty.modules.restaurants.RestaurantsFragment;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends BaseActivity {
    BottomNavigationView bottomNav = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.balance);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

            initPermission();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new GerenteFragment()).commit();
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
        if (ProfileDataSingleton.getInstance().isLogAsGerente()) {
            selectedFragment = new GerenteFragment();
        }else{
            if (itemId == R.id.balance) {
                selectedFragment = new BalanceFragment();
            } else if (itemId == R.id.favorites) {
                selectedFragment = new MisFavoritosFragment();
            } else if (itemId == R.id.notification) {
                selectedFragment = new NotificationFragment();
            } else if (itemId == R.id.restaurants) {
                selectedFragment = new RestaurantsFragment();
            }
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

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("TAG", " onRequestPermissionsResult");

        if (requestCode == 99) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                // rationale
                Log.w("TAG", "denied nope");
                dialogRational();
            } else {
                if (checkSelfPermission()) {
                    //allowed
                    Log.v("TAG", "onGranted: Permission");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (getFragmentManager() != null) {


                                //GO to your scan fragment
                            }
                        }
                    }, 200);

                } else {
                    //set to never ask again
                    Log.w("TAG", "set to never ask again never");

                }
            }
        }
    }

    private void dialogRational() {
        Log.d("TAG", "dialogRational: ");
        new AlertDialog.Builder(this)
                .setCancelable(false).setTitle("Warning")
                .setMessage("You must grant the permission, " +
                        "if denied, then go to Setting to activate manually ")
                .setPositiveButton("GRANT ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        requestPermission();
                    }
                })
                .setNeutralButton("DISMISS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    //init
    public void initPermission() {
        Log.d("TAG", "initPermission: ");
        requestPermission();

    }

    //request
    private void requestPermission() {
        Log.d("TAG", " requestPermission");
        requestPermissions(new String[]{Manifest.permission.CAMERA}
                , 99);
    }

    // this one : never ask again
    private boolean checkSelfPermission() {
        Log.d("TAG", " checkSelfPermission");
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

    }
}