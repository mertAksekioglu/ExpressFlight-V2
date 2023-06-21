package com.expressflight.ExpressFlight.util.seatMapper;

import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.enums.SeatStatus;
import com.expressflight.ExpressFlight.enums.SeatType;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import com.expressflight.ExpressFlight.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SunExpress7378HC implements ISeatMapper {

    ISeatRepository seatRepository;

    public SunExpress7378HC(ISeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat[][] mapSeats() {
        Seat[][] seats = new Seat[32][6];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                String seatCode = String.valueOf(i+1) + String.valueOf((char) (j + 65));
                if(i == 14 || i == 15) {
                    seats[i][j] = new Seat(seatCode,SeatType.PREMIUM_ECONOMY,SeatStatus.FREE);
                }
                else if(i == 0) {
                    if(j > 3) {
                        seats[i][j] = new Seat(seatCode, SeatType.UNUSABLE, SeatStatus.NOT_SEAT);
                    }
                    else {
                        seats[i][j] = new Seat(seatCode,SeatType.PREMIUM_ECONOMY,SeatStatus.FREE);
                    }

                }
                else if(i == 1 && j > 3) {
                    seats[i][j] = new Seat(seatCode,SeatType.PREMIUM_ECONOMY,SeatStatus.FREE);
                }
                else {
                    seats[i][j] = new Seat(seatCode,SeatType.ECONOMY,SeatStatus.FREE);
                }

                if(seats[i][j] != null) {
                    seats[i][j] = seatRepository.save(seats[i][j]);
                }
            }
        }
        return seats;
    }

}
