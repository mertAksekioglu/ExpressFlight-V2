package com.expressflight.ExpressFlight.enums;

public enum Gender {

    FEMALE,
    MALE,
    UNIDENTIFIED;

    public String[] shortNames = {"F", "M", "U"};


    public void printStatus() {

        for (int i = 0; i < SeatStatus.values().length ; i++) {

            if(this.equals(SeatStatus.values()[i])) {
                System.out.print(shortNames[i]);
            }

        }

    }

}


