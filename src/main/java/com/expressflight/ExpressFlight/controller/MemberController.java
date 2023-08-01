package com.expressflight.ExpressFlight.controller;

import com.expressflight.ExpressFlight.dto.MemberDTO;
import com.expressflight.ExpressFlight.requestdto.MemberRequestDTO;
import com.expressflight.ExpressFlight.service.MemberService;
import com.expressflight.ExpressFlight.serviceInterface.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/member")
@CrossOrigin(origins = "*")
public class MemberController {

    private IMemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping(value = "/get-id")
    public MemberDTO getMember(@RequestParam(value = "id") Long memberId) {
        return memberService.getMember(memberId);
    }

    @PostMapping(value = "/add-member")
    public MemberDTO addMember(@RequestBody MemberRequestDTO memberRequestDto) {
        return memberService.addMember(memberRequestDto);
    }

    @DeleteMapping(value = "/delete-id")
    public MemberDTO deleteMember(@RequestParam(value = "id") Long memberId) {
        return memberService.deleteMember(memberId);
    }

    @PutMapping(value = "/update-member")
    public MemberDTO updateMember(@RequestBody MemberRequestDTO memberRequestDto, @RequestParam(value = "id") Long memberId) {
        return memberService.updateMember(memberRequestDto, memberId);
    }

}

