package com.gcarolo.loyalty.modules.balance;

import com.gcarolo.loyalty.common.IView;
import com.gcarolo.loyalty.core.dto.balance.BalanceData;

public interface BalanceView extends IView {
    void successData(BalanceData data);
}
