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
            JSON 예시는 다음과 같다:

            {
              "feedback": "good한 점과 개선할 점을 포함한 피드백 내용을 markdown 형식으로 작성해줘.",
              "good": "답변에서 잘한 점을 구체적으로 기술해줘. 예를 들어 개념 이해나 예시 설명이 좋았던 부분.",
              "improve": "보완할 점을 구체적으로 제시해줘. 예를 들어 핵심 개념 누락, 부정확한 정의, 구체적 예시 부족 등.",
              "summary": "이번 대화를 한 문단으로 요약해줘. 사용자의 답변의 방향성과 수준을 파악할 수 있게. 다음 대화에 참고할 수 있게.",
              "score": 0 | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100
            }

            평가 기준은 다음과 같다:
            - 100: 완벽한 답변. 개념 정확 + 예시 명확 + 응용 가능성 언급.
            - 80: 전반적으로 잘 이해했지만 세부 설명이나 예시 부족.
            - 60: 기본 개념은 알지만 설명이 모호하거나 틀린 부분이 일부 있음.
            - 40: 개념 이해 부족하거나 잘못된 설명이 있음.
            - 20: 질문과 관련 없는 내용이 다수 포함.
            - 0: 답변이 거의 없거나 완전히 틀림.

            모든 출력은 반드시 위 JSON 형식만 사용해야 하며, 다른 설명 문장은 절대 포함하지 마.
            상대방을 지칭하는 것은 '개발자'라고 해주고 예의를 갖춰서 답변해.
            good와 improve 항목은 3문장 정도의 구체적이고 실질적인 피드백을 제공해줘.
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
//        System.out.println(raw);

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
