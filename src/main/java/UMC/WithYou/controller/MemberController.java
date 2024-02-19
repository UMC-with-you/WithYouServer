package UMC.WithYou.controller;

import UMC.WithYou.common.annotation.AuthorizedMember;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.dto.auth.MemberResponse;
import UMC.WithYou.dto.member.NameRequest;
import UMC.WithYou.service.member.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원 이름 변경")
    @ApiResponse(responseCode = "200", description = "이름 업데이트 성공")
    @ApiResponse(responseCode = "400", description = "이름 업데이트 실패", content = @Content(schema = @Schema(implementation = String.class)))
    @PatchMapping("/name")
    public ResponseEntity<Void> updateName(@AuthorizedMember Member member, @RequestBody NameRequest request) {
        memberService.updateName(member,request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이미지 변경")
    @ApiResponse(responseCode = "200", description = "이미지 업데이트 성공")
    @ApiResponse(responseCode = "400", description = "이미지 업데이트 실패", content = @Content(schema = @Schema(implementation = String.class)))
    @PatchMapping("/image")
    public ResponseEntity<Void> updateImage(@AuthorizedMember Member member,
                            @RequestParam("image") @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "업데이트할 이미지 파일", required = true,
                                    content = @Content(mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary"))) MultipartFile imageFile) {
        memberService.updateImage(member, imageFile);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "회원 정보 조회")
    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공"),
            @ApiResponse(responseCode = "400", description = "회원 정보 조회 실패", content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    public ResponseEntity<MemberResponse> getMember(@AuthorizedMember Member member) {
        return ResponseEntity.ok(memberService.getMember(member));
    }
}
