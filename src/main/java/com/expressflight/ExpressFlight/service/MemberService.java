package com.expressflight.ExpressFlight.service;

import com.expressflight.ExpressFlight.domain.Member;
import com.expressflight.ExpressFlight.dto.MemberDTO;
import com.expressflight.ExpressFlight.repository.IMemberRepository;
import com.expressflight.ExpressFlight.requestdto.MemberRequestDTO;
import com.expressflight.ExpressFlight.serviceInterface.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements IMemberService {

    private IMemberRepository memberRepository;

    private ModelMapper modelMapper;

    public MemberService(IMemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> convertToDTO(member))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDTO getMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) {
            throw new IllegalStateException("Member with the id " + memberId + " does not exist");
        }
        return convertToDTO(member.get());
    }



    @Override
    public MemberDTO addMember(MemberRequestDTO memberRequestDto) {
        Member member = convertToEntity(memberRequestDto);
        /*Optional<Member> existingMember = memberRepository.findByCode(member.getCode());
        if(existingMember.isPresent()) {
            throw new IllegalStateException("Member with the code " + member.getCode() + "already exists.");
        }*/
        memberRepository.save(member);
        return convertToDTO(member);
    }

    @Override
    public MemberDTO deleteMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()) {
            throw new IllegalStateException("Member with the id " + memberId + " does not exist");
        }
        memberRepository.deleteById(memberId);
        return convertToDTO(member.get());
    }

    @Override
    @Transactional
    public MemberDTO updateMember(MemberRequestDTO memberRequestDto, Long memberId) {
        Member member = convertToEntity(memberRequestDto);
        Optional<Member> existingMember = memberRepository.findById(memberId);
        if(!existingMember.isPresent()) {
            throw new IllegalStateException("Member with the email " + existingMember.get().getEmail() + " does not exist.");
        }
        if(member.getEmail() != null){
            existingMember.get().setEmail(member.getEmail());
        }
        if(member.getPassword() != null){
            existingMember.get().setPassword(member.getPassword());
        }
        if(member.getActive() != null){
            existingMember.get().setActive(member.getActive());
        }
        if(member.getPassenger() != null){
            existingMember.get().setPassenger(member.getPassenger());
        }
        return convertToDTO(existingMember.get());
    }


    private MemberDTO convertToDTO(Member member) {
        MemberDTO MemberDto = modelMapper.map(member, MemberDTO.class);
        return MemberDto;
    }

    private Member convertToEntity(MemberRequestDTO memberRequestDto) {
        Member member = modelMapper.map(memberRequestDto, Member.class);
        return member;
    }

}
