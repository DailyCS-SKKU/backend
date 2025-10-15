package cse.skku.edu.dailycs.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import cse.skku.edu.dailycs.dto.user.UserInfoDto;
import cse.skku.edu.dailycs.dto.auth.AuthCodeRequestDto;
import cse.skku.edu.dailycs.dto.auth.LoginUserDto;
import cse.skku.edu.dailycs.service.auth.GoogleLoginService;
import cse.skku.edu.dailycs.service.auth.JwtService;
import cse.skku.edu.dailycs.service.user.UserAuthService;
import cse.skku.edu.dailycs.service.user.UserInfoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GoogleLoginService googleLoginService;
    private final UserAuthService userAuthService;
    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    @Value("${JWT_REFRESH_EXPIRATION_TTL}")
    private Duration refreshTtl;

    @GetMapping("/test")
    public String test() {
        return "Auth test successful!";
    }


    @PostMapping("/login/google")
    public ResponseEntity<UserInfoDto> googleLogin(
            @RequestBody AuthCodeRequestDto request,
            HttpServletResponse response
    ) throws JsonProcessingException {

        String googleAccessToken = googleLoginService.requestAccessToken(request.getCode());
        LoginUserDto googleUserDto = googleLoginService.getUserInfoByAccessToken(googleAccessToken);
        UserInfoDto userInfo = userAuthService.createUserIfNotExists(googleUserDto);

        String accessToken = jwtService.generateAccessToken(userInfo.getId(), userInfo.getEmail(), userInfo.getStatus());
        String refreshToken = jwtService.generateRefreshToken(userInfo.getId());

        response.addHeader("Authorization", "Bearer " + accessToken);
        addCookie(response, "refreshToken", refreshToken, (int) refreshTtl.getSeconds());  // 7일

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Cookie> refreshTokenCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst();

        if (refreshTokenCookie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String tokenValue = refreshTokenCookie.get().getValue();

        // refreshToken 유효성 검사
        if (!jwtService.isValid(tokenValue)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = jwtService.getUserId(tokenValue);

        UserInfoDto userInfo = userInfoService.getUserInfoByUserId(userId);

        String accessToken = jwtService.generateAccessToken(userInfo.getId(), userInfo.getEmail(), userInfo.getStatus());

        response.addHeader("Authorization", "Bearer " + accessToken);

        return ResponseEntity.ok("Reissue Successfully");
    }


    private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);  // JavaScript 접근 방지
        cookie.setSecure(true);  // HTTPS에서만 전송
        cookie.setPath("/");  // 모든 경로에서 사용 가능
        cookie.setMaxAge(maxAge);  // 쿠키 유효 기간 설정
        cookie.setAttribute("SameSite", "Strict"); // CSRF 방지

        response.addCookie(cookie);
    }
}
