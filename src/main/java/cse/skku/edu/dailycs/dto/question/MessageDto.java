package cse.skku.edu.dailycs.dto.question;

import cse.skku.edu.dailycs.entity.AttemptMessage;
import cse.skku.edu.dailycs.util.enumType.MessageRole;
import cse.skku.edu.dailycs.util.enumType.MessageStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MessageDto {

    private Long messageId;
    private int turnNumber;
    private MessageRole role;
    private String content;
    private MessageStatus status;
    private LocalDateTime createdAt;

    public static MessageDto fromAttemptMessage(AttemptMessage attemptMessage) {
        return MessageDto.builder()
                .messageId(attemptMessage.getId())
                .turnNumber(attemptMessage.getTurnNo())
                .role(attemptMessage.getRole())
                .content(attemptMessage.getContent())
                .status(attemptMessage.getStatus())
                .createdAt(attemptMessage.getCreatedAt())
                .build();
    }
}
