package com.gcarolo.loyalty.modules.recoveryPassword;

import com.gcarolo.loyalty.common.IView;
import com.gcarolo.loyalty.core.dto.balance.BalanceData;

public interface RecoveryPasswordView extends IView {
    void successSendOTP();
    void successValidateOTP();
}
