package com.gcarolo.loyalty.modules.createAccount;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.WelcomeActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.core.params.createAccount.Gender;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;
import com.gcarolo.loyalty.utilities.DatePickerFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class CreateAccountFragment extends BaseFragment implements CreateAccountView, View.OnClickListener {

    private CreateAccountPresenter presenter;
    private View rootView;
    int SELECT_PICTURE = 200;

    CircleImageView imgProfile;

    int genderSelect = 0;

    TextInputEditText edMail;
    TextInputEditText edPassword;
    AutoCompleteTextView edDay;
    AutoCompleteTextView edMonth;
    AutoCompleteTextView edYear;

    public CreateAccountFragment() {
        presenter = new CreateAccountPresenter(this);
    }


    public static CreateAccountFragment newInstance() {
        CreateAccountFragment fragment = new CreateAccountFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configViews() {

        TextInputEditText edName = rootView.findViewById(R.id.ed_name);
        edMail = rootView.findViewById(R.id.ed_mail);
        TextInputEditText edCodePhone = rootView.findViewById(R.id.ed_code_phone);
        TextInputEditText edPhoneNumber = rootView.findViewById(R.id.ed_phone_number);
        edDay = rootView.findViewById(R.id.ed_day);
        edMonth = rootView.findViewById(R.id.ed_month);
        edYear = rootView.findViewById(R.id.ed_year);
        AutoCompleteTextView edGender = rootView.findViewById(R.id.ed_gender);
        edPassword = rootView.findViewById(R.id.ed_password);
        TextInputEditText edConfirmPassword = rootView.findViewById(R.id.ed_confirm_password);
        CheckBox checkTermsConditions = rootView.findViewById(R.id.btn_check_terms_conditions);

        edDay.setOnClickListener(this);
        edMonth.setOnClickListener(this);
        edYear.setOnClickListener(this);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Gender_Names));
        String selection;
        AutoCompleteTextView acTV1 = rootView.findViewById(R.id.ed_gender);
        acTV1.setAdapter(arrayAdapter);
        acTV1.setCursorVisible(false);
        acTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genderSelect = position;
            }
        });

        acTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                acTV1.showDropDown();
            }
        });

        Button btnRegister = rootView.findViewById(R.id.btn_continue);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edName.getEditableText().toString().isEmpty() && !edMail.getEditableText().toString().isEmpty()
                && !edCodePhone.getEditableText().toString().isEmpty() && !edPhoneNumber.getEditableText().toString().isEmpty()
                && !edDay.getEditableText().toString().isEmpty() && !edMonth.getEditableText().toString().isEmpty()
                && !edYear.getEditableText().toString().isEmpty() && !edGender.getEditableText().toString().isEmpty()
                && !edPassword.getEditableText().toString().isEmpty() && !edConfirmPassword.getEditableText().toString().isEmpty()
                && checkTermsConditions.isChecked()) {
                    if (edPassword.getEditableText().toString().equalsIgnoreCase(edConfirmPassword.getEditableText().toString())) {
                        Gender gender = new Gender();
                        gender.setId(genderSelect);
                        if (genderSelect == 0) {
                            gender.setValorSexousuario("OTRO");
                        } else if (genderSelect == 1) {
                            gender.setValorSexousuario("MUJER");
                        } else if (genderSelect == 2) {
                            gender.setValorSexousuario("HOMBRE");
                        }
                        presenter.registerUser(edName.getEditableText().toString(), edMail.getEditableText().toString(), edCodePhone.getEditableText().toString(),
                                edPhoneNumber.getEditableText().toString(), edYear.getEditableText().toString()+"-"+edMonth.getEditableText().toString()+"-"+edDay.getEditableText().toString(),
                                gender, edPassword.getEditableText().toString());
                    } else {
                        showWarningAlert("Favor de validar las contraseñas");
                    }
                }   else{
                    showWarningAlert("Debes de capturar toda la información así como aceptar nuestros Términos y Condiciones");
                }
            }
        });

        imgProfile = rootView.findViewById(R.id.profile_image);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

    }

    @Override
    public void onClick(View view) {
        showDatePickerDialog();
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                // +1 because January is zero
                if (day < 10) {
                    edDay.setText("0" + day);
                }else{
                    edDay.setText(""+day);
                }
                if (month < 10) {
                    edMonth.setText("0" + month);
                }else{
                    edMonth.setText(""+month);
                }
                edYear.setText(""+year);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
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

    @Override
    public void successRegister() {
        showSuccessAlert("Se hizo el registro satisfactoriamente");
        presenter.loginUser(edMail.getEditableText().toString(), edPassword.getEditableText().toString());
    }

    @Override
    public void successLogin(String token, int id, String fullname) {
        ProfileDataSingleton.getInstance().setToken(token);
        ProfileDataSingleton.getInstance().setUserId(id);
        ProfileDataSingleton.getInstance().setUsername(edMail.getText().toString());
        ProfileDataSingleton.getInstance().setFullname(fullname);

        WelcomePageFragment fragment = WelcomePageFragment.newInstance();
        displayFragment(fragment, null);

        Intent i = new Intent(getActivity(), WelcomeActivity.class);
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
        getActivity().finish();
    }
}