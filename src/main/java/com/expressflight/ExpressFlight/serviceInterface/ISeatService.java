package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.requestdto.SeatRequestDTO;

import java.util.List;

public interface ISeatService {

    public List<SeatDTO> getAllSeats();

    public SeatDTO getSeat(Long seatId);

    public SeatDTO getSeatByCode(String seatCode);

    public SeatDTO addSeat(SeatRequestDTO seatRequestDto);

    public SeatDTO deleteSeat(Long seatId);

    public SeatDTO updateSeat(SeatRequestDTO seatRequestDto, Long seatId);

}
