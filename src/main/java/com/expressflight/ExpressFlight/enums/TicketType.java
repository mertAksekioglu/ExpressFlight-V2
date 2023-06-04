package com.expressflight.ExpressFlight.enums;

public enum TicketType {

    UNKNOWN,
    ADULT,
    CHILD,
    INFANT;

    public String[] shortNames = {" ", "A","C","I"};

    public void printType() {

        for (int i = 0; i < TicketType.values().length ; i++) {

            if(this.equals(TicketType.values()[i])) {
                System.out.print(shortNames[i]);
            }

        }

    }

}
