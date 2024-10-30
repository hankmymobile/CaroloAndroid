package com.gcarolo.loyalty.core;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.gcarolo.loyalty.core.dto.ApiError;

public interface ApiCallBack {
    void onSuccessResponse(RequestCodeEnum requestCode, ApiDto dto);
    void onErrorResponse(RequestCodeEnum requestCode, ApiError errorDto);
}
