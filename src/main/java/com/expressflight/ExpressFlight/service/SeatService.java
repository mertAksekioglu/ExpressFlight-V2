package com.expressflight.ExpressFlight.service;


import com.expressflight.ExpressFlight.domain.Seat;
import com.expressflight.ExpressFlight.dto.SeatDTO;
import com.expressflight.ExpressFlight.repository.ISeatRepository;
import com.expressflight.ExpressFlight.requestdto.SeatRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.ISeatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService {

    private ISeatRepository seatRepository;

    private ModelMapper modelMapper;

    public SeatService(ISeatRepository seatRepository, ModelMapper modelMapper) {
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .map(seat -> convertToDTO(seat))
                .collect(Collectors.toList());
    }

    @Override
    public SeatDTO getSeat(Long seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the id " + seatId + " does not exist");
        }
        return convertToDTO(seat.get());
    }

    @Override
    public SeatDTO getSeatByCode(String seatCode) {
        Optional<Seat> seat = seatRepository.findByCode(seatCode);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + seat.get().getCode() + "does not exist.");
        }
        return convertToDTO(seat.get());
    }

    @Override
    public SeatDTO addSeat(SeatRequestDTO seatRequestDto) {
        Seat seat = convertToEntity(seatRequestDto);
        /*
        Optional<Seat> existingSeat = seatRepository.findByCode(seat.getCode());
        if(existingSeat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + seat.getCode() + "already exists.");
        }
        */
        seatRepository.save(seat);
        return convertToDTO(seat);
    }

    @Override
    public SeatDTO deleteSeat(Long seatId) {
        Optional<Seat> seat = seatRepository.findById(seatId);
        if(!seat.isPresent()) {
            throw new IllegalStateException("Seat with the id " + seatId + " does not exist");
        }
        seatRepository.deleteById(seatId);
        return convertToDTO(seat.get());
    }

    @Override
    @Transactional
    public SeatDTO updateSeat(SeatRequestDTO seatRequestDto, Long seatId) {
        Seat seat = convertToEntity(seatRequestDto);
        Optional<Seat> existingSeat = seatRepository.findById(seatId);
        if(!existingSeat.isPresent()) {
            throw new IllegalStateException("Seat with the code " + existingSeat.get().getCode() + " does not exist.");
        }
        if(seat.getCode() != null){
            existingSeat.get().setCode(seat.getCode());
        }
        if(seat.getType() != null){
            existingSeat.get().setType(seat.getType());
        }
        if(seat.getStatus() != null){
            existingSeat.get().setStatus(seat.getStatus());
        }
        return convertToDTO(existingSeat.get());
    }

    private SeatDTO convertToDTO(Seat seat) {
        SeatDTO SeatDto = modelMapper.map(seat, SeatDTO.class);
        return SeatDto;
    }

    private Seat convertToEntity(SeatRequestDTO seatRequestDto) {
        Seat seat = modelMapper.map(seatRequestDto, Seat.class);
        return seat;
    }
    
}
