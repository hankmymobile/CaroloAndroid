package com.gcarolo.loyalty.common;

import static com.gcarolo.loyalty.common.BaseActivity.ALERT_TYPE_ERROR;
import static com.gcarolo.loyalty.common.BaseActivity.ALERT_TYPE_SUCCESS;
import static com.gcarolo.loyalty.common.BaseActivity.ALERT_TYPE_WARNING;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment implements IView {

    public static String URL = "url";
    private String tagName = getClass().getCanonicalName();
    private OnFragmenInteractionListener fragmentListener;

    public String getTagName() {
        String last = tagName.substring(tagName.lastIndexOf('.') + 1);
        return last;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentListener = (OnFragmenInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void showProgressDialog() {
        if (null != fragmentListener) {
            fragmentListener.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (null != fragmentListener) {
            fragmentListener.hideProgressDialog();
        }
    }

    @Override
    public void showErrorAlert(String alert) {
        if (null != fragmentListener){
            fragmentListener.showAlert(alert, ALERT_TYPE_ERROR);
        }
    }

    @Override
    public void showWarningAlert(String alert) {
        if (null != fragmentListener){
            fragmentListener.showAlert(alert, ALERT_TYPE_WARNING);
        }
    }

    @Override
    public void showSuccessAlert(String alert) {
        if (null != fragmentListener){
            fragmentListener.showAlert(alert, ALERT_TYPE_SUCCESS);
        }
    }

    public void displayFragment(BaseFragment nextFragment, Bundle bundle) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = ((BaseActivity) getActivity());
            activity.displayFragment(nextFragment, bundle, nextFragment.getTagName(), true);
        }
    }

    public void replaceFragment(BaseFragment nextFragment) {
        if (getActivity() instanceof BaseActivity) {
            BaseActivity activity = ((BaseActivity) getActivity());
            activity.replaceFragment(nextFragment, nextFragment.getTagName());
        }
    }

}
