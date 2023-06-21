package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.SeatDTO;

import java.util.List;

public interface ISeatService {

    public List<SeatDTO> getAllSeats();

    public SeatDTO getSeat(Long seatId);

    public SeatDTO getSeatByCode(String seatCode);

    public SeatDTO addSeat(SeatDTO seat);

    public SeatDTO deleteSeat(Long seatId);

    public SeatDTO updateSeat(SeatDTO seat, Long seatId);

}
