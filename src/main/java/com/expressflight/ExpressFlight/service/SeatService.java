package com.expressflight.ExpressFlight.service;


import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.integration.SunExpressIntegration;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import com.expressflight.ExpressFlight.serviceInterface.ISeatService;
import com.expressflight.ExpressFlight.util.IWriter;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService implements ISeatService {

    @Autowired
    private ISeatRepository seatRepository;


    @Autowired
    private ModelMapper modelMapper;
    



    @Override
    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        List<SeatDTO> seatDtos = new ArrayList<>();

        for (Seat seat : seats) {
            SeatDTO seatDto = modelMapper.map(seat, SeatDTO.class);
            seatDtos.add(seatDto);
        }

        return seatDtos;
    }
    @Override
    public SeatDTO getSeat(Long seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the id " + seatId + " does not exist");
        }
        SeatDTO returningSeatDto = modelMapper.map(seat.get(), SeatDTO.class);
        return returningSeatDto;
    }


    @Override
    public SeatDTO getSeatByCode(String seatCode) {
        Optional<Seat> seat = seatRepository.findByCode(seatCode);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + seat.get().getCode() + "does not exist.");
        }
        SeatDTO returningSeatDto = modelMapper.map(seat.get(), SeatDTO.class);
        return returningSeatDto;
    }




    @Override
    public SeatDTO addSeat(SeatDTO seatDto) {
        Seat seat = modelMapper.map(seatDto,Seat.class);
        /*
        Optional<Seat> existingSeat = seatRepository.findByCode(seat.getCode());
        if(existingSeat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + seat.getCode() + "already exists.");
        }
        */
        seatRepository.save(seat);

        SeatDTO returningSeatDto = modelMapper.map(seat, SeatDTO.class);
        return returningSeatDto;
    }

    @Override
    public SeatDTO deleteSeat(Long seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the id " + seatId + " does not exist");
        }
        seatRepository.deleteById(seatId);
        SeatDTO returningSeatDto = modelMapper.map(seat.get(), SeatDTO.class);
        return returningSeatDto;
    }

    @Override
    @Transactional
    public SeatDTO updateSeat(SeatDTO seatDto, Long seatId) {
        Seat seat = modelMapper.map(seatDto,Seat.class);
        Optional<Seat> existingSeat = seatRepository.findById(seatId);
        if(!existingSeat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + existingSeat.get().getCode() + " does not exist.");
        }
        if(seat.getCode() != null){
            existingSeat.get().setCode(seatDto.getCode());
        }
        if(seat.getType() != null){
            existingSeat.get().setType(seatDto.getType());
        }
        if(seat.getStatus() != null){
            existingSeat.get().setStatus(seatDto.getStatus());
        }

        SeatDTO returningSeatDto = modelMapper.map(existingSeat.get(), SeatDTO.class);
        return returningSeatDto;

    }

}
