package com.gcarolo.loyalty.modules.createAccount;

import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.ApiClient;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;
import com.gcarolo.loyalty.core.dto.login.LoginDTO;
import com.gcarolo.loyalty.core.params.createAccount.CreateAccountParams;
import com.gcarolo.loyalty.core.params.createAccount.Gender;
import com.gcarolo.loyalty.core.params.login.UserLoginParams;
import com.google.android.material.textfield.TextInputEditText;

public class CreateAccountPresenter extends BasePresenter<CreateAccountView> {

    public CreateAccountPresenter(CreateAccountView view) {
        super(view);
    }

    public void registerUser(String name, String firstName, String lastName, String mail, String code, String phoneNumber, String birthday, Gender gender, String password) {
        getView().showProgressDialog();

        CreateAccountParams params = new CreateAccountParams();
        params.setUsername(mail);
        params.setNombre(name);
        params.setApellidos(firstName + " " + lastName);
        params.setPassword(password);
        params.setAutoriza(true);
        params.setNumeroCelular(phoneNumber);
        params.setFechaNacimiento(birthday);
        params.setSexoUsuario(gender);

        ApiClient.getInstance().registerUser(params, this);
    }

    public void loginUser(String username, String password) {
        UserLoginParams params = new UserLoginParams();
        params.setUsername(username);
        params.setPassword(password);
        ApiClient.getInstance().loginUser(params, this);
    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        switch (requestCode) {
            case USER_REGISTER:
                getView().successRegister();
                break;
            case USER_LOGIN:
                getView().hideProgressDialog();
                getView().successLogin(((LoginDTO)dto).getData().getToken(), ((LoginDTO)dto).getData().getUserId(), ((LoginDTO)dto).getData().getFullName());
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        getView().hideProgressDialog();
        switch (requestCode) {
            case USER_REGISTER:
                view.showErrorAlert(errorDto.getMensaje());
                break;
            case USER_LOGIN:
                getView().showErrorAlert(errorDto.getMensaje());
                break;
        }
    }

}
