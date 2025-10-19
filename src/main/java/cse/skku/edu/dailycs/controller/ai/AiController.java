package cse.skku.edu.dailycs.controller.ai;

import cse.skku.edu.dailycs.dto.ai.AiRequestDto;
import cse.skku.edu.dailycs.dto.ai.AiResponseDto;
import cse.skku.edu.dailycs.service.ai.AiService;
import cse.skku.edu.dailycs.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;
    private final JwtService jwtService;

    @PostMapping("/ask/no-history")
    public ResponseEntity<AiResponseDto> askWithNoHistory(
            @RequestBody AiRequestDto aiRequestDto,
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        AiResponseDto response = aiService.askWithNoHistory(aiRequestDto);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/ask/with-history")
    public ResponseEntity<AiResponseDto> askWithHistory(
            @RequestBody AiRequestDto aiRequestDto,
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        AiResponseDto response = aiService.askWithHistory(userId, aiRequestDto);

        return ResponseEntity.ok().body(response);
    }
}
