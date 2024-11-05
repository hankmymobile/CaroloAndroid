package com.gcarolo.loyalty.modules.createAccount;

import com.gcarolo.loyalty.common.BasePresenter;
import com.gcarolo.loyalty.core.RequestCodeEnum;
import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;

public class CreateAccountPresenter extends BasePresenter<CreateAccountView> {

    public CreateAccountPresenter(CreateAccountView view) {
        super(view);
    }

    String profileId = "";
    String gender = "";

    public void getProfiles() {
        getView().showProgressDialog();
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void registerUser(String name, String age, String email, String phoneNumber, String password, String notes) {

    }

    @Override
    public void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto) {
        super.onSuccessResponse(requestCode, dto);
        switch (requestCode) {
            case GET_PROFILES:
                //this.validateDataProfiles((ProfilesDto) dto);
                break;
        }
    }

    @Override
    public void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto) {
        super.onErrorResponse(requestCode, errorDto);
        switch (requestCode) {
            case GET_PROFILES:
                //this.validateDataProfiles(null);
                break;
        }
    }

}
