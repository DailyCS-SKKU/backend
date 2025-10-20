package cse.skku.edu.dailycs.service.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import cse.skku.edu.dailycs.dto.ai.AiRequestDto;
import cse.skku.edu.dailycs.dto.ai.AiResponseDto;
import cse.skku.edu.dailycs.entity.AttemptMessage;
import cse.skku.edu.dailycs.entity.Question;
import cse.skku.edu.dailycs.entity.UserQuestion;
import cse.skku.edu.dailycs.entity.UserQuestionAttempt;
import cse.skku.edu.dailycs.entity.id.UserQuestionId;
import cse.skku.edu.dailycs.repository.AttemptMessageRepository;
import cse.skku.edu.dailycs.repository.QuestionRepository;
import cse.skku.edu.dailycs.repository.UserQuestionAttemptRepository;
import cse.skku.edu.dailycs.repository.UserQuestionRepository;
import cse.skku.edu.dailycs.util.enumType.MessageRole;
import cse.skku.edu.dailycs.util.enumType.MessageStatus;
import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiService {

    private final UserQuestionRepository userQuestionRepository;
    private final UserQuestionAttemptRepository userQuestionAttemptRepository;
    private final AttemptMessageRepository attemptMessageRepository;
    private final QuestionRepository questionRepository;
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    private static String prompt = """
            너는 지금부터 개발자를 위한 CS 면접 대비를 돕는 조언자야.
            사용자는 CS 관련 질문에 답변을 제공하며, 너는 그 답변에 대해 평가하고 피드백을 제공해야 해.

            입력으로 다음 세 가지가 주어진다:
            1. question: 면접 질문 내용
            2. answer: 사용자의 답변 내용
            3. summary: 지금까지의 대화 요약 (문맥을 이해하기 위한 참고용)
            4. real_answer: 모범 답안

            너의 출력은 반드시 아래 JSON 형식을 따르며, 다른 문장은 절대 포함하지 마.
           
            {
              "feedback": "...",
              "summary": "...",
              "score": ...
            }
            
            summary는 보완할 점을 구체적으로 제시해줘. 예를 들어 핵심 개념 누락, 부정확한 정의, 구체적 예시 부족 등. 5문장 정도 성의있게 듣는 사람 입장에서 도움이 되게 작성해줘.
            score는 모범답안을 근거로 사용자의 답변을 0에서 100점 사이로 평가해줘. 0 | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100
            feedBack은 마크다운 형식으로 작성해야 해. 복사 붙여넣기를 하면 바로 마크다운으로 인식될 수 있게 작성해줘.
              - `feedback` 필드는 **Markdown 문법을 직접 사용해서 작성하라.**
              - 코드 블록(\\`\\`\\`)은 사용하지 말라.
              - 반드시 제목(`##`), 불릿(`-`), 강조(`**`), 개행(`\\n`) 문법을 활용하라.
              - `feedback`은 다음 네 가지 섹션으로 구성되어야 해:
                1. 종합 피드백: 사용자의 답변에 대한 전반적인 평가와 요약.
                2. 잘한 점: 사용자가 잘 수행한 부분을 구체적으로 3가지 이상 나열.
                3. 보완할 점: 사용자가 개선해야 할 부분을 구체적으로 3가지 이상 나열.
                4. 점수: 사용자의 답변에 대한 점수 (0에서 100 사이).
              - 형식 예시:
                ## 💬 종합 피드백
                ## ✅ 잘한 점
                ## ⚙️ 보완할 점
                ## 📈 점수
            
            모든 출력은 반드시 아래 예시 처럼 JSON 형식만 사용해야 하며, 다른 설명 문장은 절대 포함하지 마.
            모든 필드는 값이 있어야돼.
            상대방을 지칭하는 것은 '개발자'라고 해주고 예의를 갖춰서 답변해.
            예시:
            {
              "feedback": "## 💬 종합 피드백\\n개발자님, 전반적으로 좋은 답변이었습니다. ...\\n\\n## ✅ 잘한 점\\n1. 개념 이해가 명확했습니다. ...\\n\\n## ⚙️ 보완할 점\\n1. 구체적인 예시가 부족했습니다. ...\\n\\n## 📈 점수\\n80",
              "good": "1. 개념 이해가 명확했습니다. ...",
              "improve": "1. 구체적인 예시가 부족했습니다. ...",
              "summary": "개발자님의 답변은 전반적으로 좋았으나, 몇 가지 보완할 점이 있습니다. ...",
              "score": 80
            }
            
            """;

    // 문제은행
    public AiResponseDto askWithNoHistory(AiRequestDto aiRequestDto) {
        Question question = questionRepository.findById(aiRequestDto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));

        String raw = chatClient.prompt()
                .system(prompt)
                .user("""
                        {
                          "question": "%s",
                          "answer": "%s",
                          "summary": "%s",
                          "real_answer": "%s"
                        }
                        """.formatted(question.getQuestion(),
                                aiRequestDto.getUserAnswer(),
                                aiRequestDto.getAiSummary(),
                                question.getAnswer()))
                .call()
                .content();

        String json = sanitizeJson(raw);
        System.out.println(raw);

        try {
            return objectMapper.readValue(json, AiResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response: " + json, e);
        }
    }

    public AiResponseDto askWithHistory(Long userId, AiRequestDto aiRequestDto) {

        AiResponseDto aiResponseDto = askWithNoHistory(aiRequestDto);

        UserQuestion userQuestion = userQuestionRepository.findById(new UserQuestionId(userId, aiRequestDto.getQuestionId()))
                .orElseThrow(() -> new IllegalArgumentException("User has not attempted this question"));

        UserQuestionAttempt userQuestionAttempt = userQuestionAttemptRepository.findByUserIdAndQuestionId(userId, aiRequestDto.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("UserQuestionAttempt not found with userId: " + userId + " and questionId: " + aiRequestDto.getQuestionId()));

        QuestionStatus status = aiResponseDto.getScore() >= 60 ? QuestionStatus.CORRECT : QuestionStatus.WRONG;
        MessageStatus messageStatus = aiResponseDto.getScore() >= 60 ? MessageStatus.CORRECT : MessageStatus.WRONG;

//        Use_question_attempts 변경
        userQuestionAttempt.update(
                aiRequestDto.getUserAnswer(),
                aiResponseDto.getFeedback(),
                aiResponseDto.getSummary(),
                status
        );
        userQuestionAttemptRepository.save(userQuestionAttempt);

//        attempt_messages 추가
        AttemptMessage userMessage = AttemptMessage.builder()
                .attempt(userQuestionAttempt)
                .role(MessageRole.USER)
                .content(aiRequestDto.getUserAnswer())
                .status(messageStatus)
                .build();
        attemptMessageRepository.save(userMessage);

        AttemptMessage assistantMessage = AttemptMessage.builder()
                .attempt(userQuestionAttempt)
                .role(MessageRole.ASSISTANT)
                .content(aiResponseDto.getFeedback())
                .status(MessageStatus.ASSISTANT)
                .build();
        attemptMessageRepository.save(assistantMessage);

//        user_question 변경
        userQuestion.updateStatus(status);
        userQuestionRepository.save(userQuestion);

        return aiResponseDto;
    }


    private String sanitizeJson(String raw) {
        if (raw == null) return "{}";
        int start = raw.indexOf('{');
        int end = raw.lastIndexOf('}');
        if (start >= 0 && end >= 0 && end > start) {
            return raw.substring(start, end + 1).trim();
        }
        return raw.trim();
    }
}
