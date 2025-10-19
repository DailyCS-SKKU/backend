package cse.skku.edu.dailycs.dto.question;

import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ChatRoomDto {

    private UserQuestionDto question;
    private List<MessageDto> messages;

    public ChatRoomDto from(UserQuestionDto question, List<MessageDto> messages) {
        this.question = question;
        this.messages = messages;
        return this;
    }
}
