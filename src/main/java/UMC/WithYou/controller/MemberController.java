package UMC.WithYou.controller;

import UMC.WithYou.common.annotation.AuthorizedMember;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.dto.member.NameRequest;
import UMC.WithYou.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 이름 변경")
    @PatchMapping("/name")
    public void updateName(@AuthorizedMember Member member, @RequestBody NameRequest request) {
        memberService.updateName(member,request);
    }
}
