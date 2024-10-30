package com.gcarolo.loyalty.common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.utilities.AppContext;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class BaseActivity extends AppCompatActivity implements OnFragmenInteractionListener {

    public ProgressDialog mProgressDialog;
    public static int ALERT_TYPE_ERROR = 0;
    public static int ALERT_TYPE_WARNING = 1;
    public static int ALERT_TYPE_SUCCESS = 2;
    public static int ALERT_TYPE_INFO = 3;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppContext.getInstance().setContext(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppContext.getInstance().setContext(this);
    }

    @Override
    public void showAlert(@NonNull String title, int type) {
        if (null == findViewById(R.id.frameLayout)) {
            return;
        }

        boolean isLongTitle = (title.length() > 60);
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.frameLayout), "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout view = (Snackbar.SnackbarLayout) snackbar.getView();

        TextView tv = (TextView) view.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setVisibility(View.INVISIBLE);

        View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);
        TextView textViewTop = (TextView) snackView.findViewById(R.id.text);
        ImageView imageClose = (ImageView) snackView.findViewById(R.id.close_snack);
        imageClose.setVisibility(isLongTitle ? View.VISIBLE : View.GONE);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        imageClose.setColorFilter((type == ALERT_TYPE_ERROR) ?
                Color.rgb(105, 35, 38) :
                (type == ALERT_TYPE_WARNING) ?
                        Color.rgb(128, 100, 32) :
                        (type == ALERT_TYPE_INFO) ?
                                Color.rgb(255, 255, 255) :
                                Color.rgb(42, 85, 42));


        View separator = (View) snackView.findViewById(R.id.separator_alert);
        separator.setBackgroundColor((type == ALERT_TYPE_ERROR) ?
                Color.rgb(105, 35, 38) :
                (type == ALERT_TYPE_WARNING) ?
                        Color.rgb(128, 100, 32) :
                        (type == ALERT_TYPE_INFO) ?
                                Color.rgb(255, 255, 255) :
                                Color.rgb(42, 85, 42));

        textViewTop.setText(title);
        textViewTop.setTextColor((type == ALERT_TYPE_ERROR) ?
                Color.rgb(105, 35, 38) :
                (type == ALERT_TYPE_WARNING) ?
                        Color.rgb(128, 100, 32) :
                        (type == ALERT_TYPE_INFO) ?
                                Color.rgb(255, 255, 255) :
                                Color.rgb(42, 85, 42));

        snackView.setBackgroundColor((type == ALERT_TYPE_ERROR) ?
                Color.rgb(243, 216, 218) :
                (type == ALERT_TYPE_WARNING) ?
                        Color.rgb(253, 242, 209) :
                        (type == ALERT_TYPE_INFO) ?
                                getColor(R.color.danger_color) :
                                Color.rgb(217, 236, 219));

        view.setPadding(0, 0, 0, 0);
        view.addView(snackView, 0);

        snackbar.show();
    }

    @Override
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading ...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void displayFragment(BaseFragment baseFragment, Bundle bundle, @NotNull String tag, boolean addToBackStack) {
        if (null == findViewById(R.id.frameLayout)) {
            return;
        }
        if (bundle != null) {
            baseFragment.setArguments(bundle);
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left, R.anim.slide_from_left, R.anim.slide_to_right);
        fragmentTransaction.add(R.id.frameLayout, baseFragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag).setReorderingAllowed(true);
        }
        fragmentTransaction.commit();

    }

    public void replaceFragment(BaseFragment baseFragment, @NotNull String tag) {
        if (null == findViewById(R.id.frameLayout)) {
            return;
        }
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, baseFragment, tag).commit();
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                    }
                }
            });
}
