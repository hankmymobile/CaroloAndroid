package com.gcarolo.loyalty.modules.perfil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.address.AddAddressFragment;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountPresenter;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class PerfilFragment extends BaseFragment {

    private View rootView;
    int SELECT_PICTURE = 200;
    CircleImageView imgProfile;
    public PerfilFragment() {

    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == this.rootView) {
            this.rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
            configViews();
        }

        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configViews() {
        imgProfile = rootView.findViewById(R.id.profile_image);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        LinearLayout lnAddress = rootView.findViewById(R.id.lnAddress);
        lnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAddressFragment fragment = AddAddressFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

    }

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        openGallery.launch(i);
    }

    ActivityResultLauncher<Intent> openGallery
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    getActivity().getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgProfile.setImageBitmap(selectedImageBitmap);
                    }
                }
            });
}