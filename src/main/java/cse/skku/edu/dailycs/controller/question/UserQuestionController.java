package cse.skku.edu.dailycs.controller.question;

import cse.skku.edu.dailycs.dto.question.ChatRoomDto;
import cse.skku.edu.dailycs.dto.question.UserQuestionDto;
import cse.skku.edu.dailycs.entity.Question;
import cse.skku.edu.dailycs.repository.QuestionRepository;
import cse.skku.edu.dailycs.service.auth.JwtService;
import cse.skku.edu.dailycs.service.question.UserQuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class UserQuestionController {

    private final UserQuestionService userQuestionService;
    private final JwtService jwtService;

    @GetMapping("/get-list")
    public ResponseEntity<List<UserQuestionDto>> getUserQuestions(
            @RequestParam Long skillId,
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        List<UserQuestionDto> result = userQuestionService.getUserQuestionsBySkillId(userId, skillId);
//        List<UserQuestionDto> userQuestionsBySkillId = userQuestionService.getUserQuestionsBySkillId(userId, skillId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/get-info")
    public ResponseEntity<ChatRoomDto> getChatRoom(
            @RequestParam Long questionId,
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        ChatRoomDto result = userQuestionService.getChatRoom(userId, questionId);

        return ResponseEntity.ok().body(result);
    }
}
