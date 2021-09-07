package com.aurora.intreview;

public enum CountryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIVE(5, "赵"), SIX(6, "魏");

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    private Integer retCode;

    private String retMessage;


    CountryEnum(int retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach(int retCode) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (retCode == countryEnum.getRetCode()) {
                return countryEnum;
            }
        }
        return null;
    }
}
