package UMC.WithYou.controller;

import UMC.WithYou.dto.auth.LoginRequest;
import UMC.WithYou.dto.auth.LoginResponse;
import UMC.WithYou.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "로그인 API")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "로그인 실패", content = @io.swagger.v3.oas.annotations.media.Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok()
                .body(authService.authenticateOrRegisterUser(request));
    }
    
}
