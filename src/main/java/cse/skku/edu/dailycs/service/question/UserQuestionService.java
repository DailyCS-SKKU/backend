package cse.skku.edu.dailycs.service.question;

import cse.skku.edu.dailycs.dto.question.ChatRoomDto;
import cse.skku.edu.dailycs.dto.question.MessageDto;
import cse.skku.edu.dailycs.dto.question.UserQuestionDto;
import cse.skku.edu.dailycs.entity.*;
import cse.skku.edu.dailycs.entity.id.UserQuestionId;
import cse.skku.edu.dailycs.repository.*;
import cse.skku.edu.dailycs.service.message.AttemptMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserQuestionService {

    private  final UserRepository userRepository;
    private final UserQuestionRepository userQuestionRepository;
    private final QuestionRepository questionRepository;
    private final UserQuestionAttemptRepository userQuestionAttemptRepository;
    private final AttemptMessageRepository attemptMessageRepository;
    private final AttemptMessageService attemptMessageService;

    public List<UserQuestionDto> getUserQuestionsBySkillId(Long userId, Long skillId) {
        return questionRepository.findAllQuestionsByUserIdAndSkillId(skillId, userId);
    }

    public void createUserQuestion(Long userId, Long questionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + questionId));

        UserQuestion userQuestion = new UserQuestion(user, question);

        userQuestionRepository.save(userQuestion);

    }

    public void createUserQuestionAttempt(Long userId, Long questionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found with id: " + questionId));

        UserQuestionAttempt userQuestionAttempt = new UserQuestionAttempt(user, question);

        userQuestionAttemptRepository.save(userQuestionAttempt);

    }

    public ChatRoomDto getChatRoom(Long userId, Long questionId) {
        if (!userQuestionRepository.existsByUserIdAndQuestionId(userId, questionId)) {
            createUserQuestion(userId, questionId);
        }

        if (!userQuestionAttemptRepository.existsByUserIdAndQuestionId(userId, questionId)) {
            createUserQuestionAttempt(userId, questionId);
        }

        UserQuestionAttempt userQuestionAttempt = userQuestionAttemptRepository.findByUserIdAndQuestionId(userId, questionId)
                .orElseThrow(() -> new IllegalArgumentException("UserQuestionAttempt not found with userId: " + userId + " and questionId: " + questionId));

        UserQuestionId userQuestionId = new UserQuestionId(userId, questionId);
        UserQuestion userQuestion = userQuestionRepository.findById(userQuestionId)
                .orElseThrow(() -> new IllegalArgumentException("UserQuestion not found with userId: " + userId + " and questionId: " + questionId));
        UserQuestionDto userQuestionDto = new UserQuestionDto(userQuestion.getQuestion(), userQuestion);

        List<AttemptMessage> messages = attemptMessageRepository.findAllByAttemptId(userQuestionAttempt.getId());
        if (messages.isEmpty()) {
            attemptMessageService.createFristAttemptMessage(userQuestionAttempt, userQuestionDto.getQuestion());
            messages = attemptMessageRepository.findAllByAttemptId(userQuestionAttempt.getId());
        }

        List<MessageDto> messageDtos = messages.stream()
                .map(MessageDto::fromAttemptMessage)
                .collect(Collectors.toList());

        return new ChatRoomDto().from(userQuestionDto, messageDtos);
    }
}
