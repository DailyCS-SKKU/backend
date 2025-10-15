package cse.skku.edu.dailycs.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import cse.skku.edu.dailycs.controller.user.UserInfoDto;
import cse.skku.edu.dailycs.dto.auth.AuthCodeRequestDto;
import cse.skku.edu.dailycs.dto.auth.LoginUserDto;
import cse.skku.edu.dailycs.service.auth.GoogleLoginService;
import cse.skku.edu.dailycs.service.user.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GoogleLoginService googleLoginService;
    private final UserAuthService userAuthService;

    @GetMapping("/test")
    public String test() {
        return "Auth test successful!";
    }


    @PostMapping("/login/google")
    public UserInfoDto googleLogin(@RequestBody AuthCodeRequestDto request) throws JsonProcessingException {
        String accessToken = googleLoginService.requestAccessToken(request.getCode());
        LoginUserDto googleUserDto = googleLoginService.getUserInfoByAccessToken(accessToken);

        return userAuthService.createUserIfNotExists(googleUserDto);
    }
}
