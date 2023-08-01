package com.expressflight.ExpressFlight.serviceInterface;

import com.expressflight.ExpressFlight.dto.MemberDTO;
import com.expressflight.ExpressFlight.requestdto.MemberRequestDTO;

import java.util.List;

public interface IMemberService {

    public List<MemberDTO> getAllMembers();

    public MemberDTO getMember(Long memberId);

    public MemberDTO addMember(MemberRequestDTO memberRequestDto);

    public MemberDTO deleteMember(Long memberId);

    public MemberDTO updateMember(MemberRequestDTO memberRequestDto, Long memberId);

}