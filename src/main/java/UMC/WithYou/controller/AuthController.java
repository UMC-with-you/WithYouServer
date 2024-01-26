package UMC.WithYou.controller;

import UMC.WithYou.dto.auth.LoginRequest;
import UMC.WithYou.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login/kakao")
    public ResponseEntity<?> loginWithKakao(@RequestBody LoginRequest request) {
        authService.loginWithKakao(tokenDto);
        return ResponseEntity.ok().body(userDto);
    }
}
