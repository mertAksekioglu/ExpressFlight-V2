package com.expressflight.ExpressFlight.enums;

public enum SeatStatus {

    NOT_SEAT,
    FREE,
    BOOKED,
    STANDBY,
    CONFIRMED_STANDBY,
    CREW,
    JUMPSEAT;

    public String[] shortNames = {" ", "F", "B", "SB2", "SB1", "C", "J"};

    public void printStatus() {

        for (int i = 0; i < SeatStatus.values().length ; i++) {

            if(this.equals(SeatStatus.values()[i])) {
                System.out.print(shortNames[i]);
            }

        }

    }



}