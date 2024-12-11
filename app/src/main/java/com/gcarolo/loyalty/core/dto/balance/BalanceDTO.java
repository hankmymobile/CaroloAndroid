package com.gcarolo.loyalty.core.dto.balance;

import com.gcarolo.loyalty.core.dto.ApiDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BalanceDTO extends ApiDto {

    @SerializedName("data")
    @Expose
    BalanceData data;

    public BalanceData getData() {
        return data;
    }

    public void setData(BalanceData data) {
        this.data = data;
    }
}

