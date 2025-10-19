package cse.skku.edu.dailycs.service.message;

import cse.skku.edu.dailycs.entity.UserQuestionAttempt;
import cse.skku.edu.dailycs.repository.AttemptMessageRepository;
import cse.skku.edu.dailycs.util.enumType.MessageRole;
import cse.skku.edu.dailycs.util.enumType.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AttemptMessageService {

    private final AttemptMessageRepository attemptMessageRepository;

    public void createFristAttemptMessage(UserQuestionAttempt userQuestionAttempt, String content) {
        attemptMessageRepository.save(
                cse.skku.edu.dailycs.entity.AttemptMessage.builder()
                        .attempt(userQuestionAttempt)
                        .content(content)
                        .role(MessageRole.ASSISTANT)
                        .turnNo(1)
                        .status(MessageStatus.ASSISTANT)
                        .build()
        );
    }
}
