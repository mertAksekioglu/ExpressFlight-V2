package com.expressflight.ExpressFlight.enums;

public enum SeatType {

    UNUSABLE,
    ECONOMY,
    PREMIUM_ECONOMY,
    BUSINESS_CLASS,
    FIRST_CLASS,
    PREMIUM_FIRST_CLASS;


    public String[] shortNames = {" ", "E","PE","B", "F", "PF"};

    public void printType() {

        for (int i = 0; i < SeatType.values().length ; i++) {

            if(this.equals(SeatType.values()[i])) {
                System.out.print(shortNames[i]);
            }

        }

    }

}
