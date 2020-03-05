package com.cyberoxi.hstpfacilities.components;

import org.springframework.stereotype.Component;

/**
 * @author Mohamad Zarei Maram
 * @version 0.0.1
 * @since 3/5/2020
 */
@Component
public class PaymentIdGenerator {

    private static final String CHARACTERISTIC_PAYMENT_ID = "2";
    private static final String EXECUTIVE_ORGANIZATION_CODE = "1047";
    private static final String PROVINCE_CODE = "65";
    private static final String BUDGET_ROW_CODE = "113551";
    private static final String SPECIFIED_INCOME_CODE = "00";

    public String forLegalPerson(String nationalId, String controlCode, String paymentType) {
        return CHARACTERISTIC_PAYMENT_ID + controlCode + EXECUTIVE_ORGANIZATION_CODE +
                PROVINCE_CODE + BUDGET_ROW_CODE + SPECIFIED_INCOME_CODE + paymentType + nationalId;
    }

    public String forNaturalPerson(String nationalCode, String paymentType, String controlCode) {
        return CHARACTERISTIC_PAYMENT_ID + controlCode + EXECUTIVE_ORGANIZATION_CODE +
                PROVINCE_CODE + BUDGET_ROW_CODE + SPECIFIED_INCOME_CODE + paymentType + nationalCode;
    }


}
