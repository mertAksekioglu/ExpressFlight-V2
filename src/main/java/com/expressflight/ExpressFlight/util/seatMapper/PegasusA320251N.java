package com.expressflight.ExpressFlight.util.seatMapper;

import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import org.springframework.stereotype.Service;

@Service
public class PegasusA320251N implements ISeatMapper {

    private ISeatRepository seatRepository;

    public PegasusA320251N(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat[][] mapSeats() {
        Seat[][] seats = new Seat[30][6];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                String seatCode = String.valueOf(i) + String.valueOf((char) (j + 65));
                if(i == 0 || i == 14 || i == 15) {
                    seats[i][j] = new Seat(seatCode,SeatType.PREMIUM_ECONOMY,SeatStatus.FREE);
                }
                else {
                    seats[i][j] = new Seat(seatCode,SeatType.ECONOMY,SeatStatus.FREE);
                }
            }
        }
        return seats;
    }

}
