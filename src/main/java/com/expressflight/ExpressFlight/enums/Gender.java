package com.expressflight.ExpressFlight.enums;

public enum Gender {

    FEMALE,
    MALE,
    UNIDENTIFIED;

    public String[] shortNames = {"F", "M", "U"};

    public void printGender() {

        for (int i = 0; i < Gender.values().length ; i++) {

            if(this.equals(Gender.values()[i])) {
                System.out.print(shortNames[i]);
            }

        }

    }

}


