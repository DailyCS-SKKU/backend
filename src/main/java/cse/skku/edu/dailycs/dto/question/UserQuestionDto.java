package cse.skku.edu.dailycs.dto.question;

import cse.skku.edu.dailycs.entity.Question;
import cse.skku.edu.dailycs.entity.UserQuestion;
import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class UserQuestionDto {

    private Long questionId;
    private String question;
    private String answer;
    private int day;

    private Long skillId;
    private String skillName;

    private Boolean bookmark;
    private QuestionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserQuestionDto(Question question, UserQuestion userQuestion) {
        this.questionId = question.getId();
        this.question = question.getQuestion();
        this.answer = question.getAnswer();
        this.day = question.getDay();
        this.skillId = question.getSkill().getId();
        this.skillName = question.getSkill().getName();

        if (userQuestion != null) {
            this.bookmark = userQuestion.isBookmark();
            this.status = userQuestion.getStatus();
            this.createdAt = userQuestion.getCreatedAt();
            this.updatedAt = userQuestion.getUpdatedAt();
        } else {
            this.bookmark = null;
            this.status = null;
            this.createdAt = null;
            this.updatedAt = null;
        }
    }

    public static UserQuestionDto fromQuestionAndUserQuestion (Question question, UserQuestion userQuestion) {
        return UserQuestionDto.builder()
                .questionId(question.getId())
                .question(question.getQuestion())
                .answer(question.getAnswer())
                .day(question.getDay())
                .skillId(question.getSkill().getId())
                .skillName(question.getSkill().getName())
                .bookmark(userQuestion.isBookmark())
                .status(userQuestion.getStatus())
                .createdAt(userQuestion.getCreatedAt())
                .updatedAt(userQuestion.getUpdatedAt())
                .build();
    }

    public static UserQuestionDto fromQuestion(Question question) {
        return UserQuestionDto.builder()
                .questionId(question.getId())
                .question(question.getQuestion())
                .answer(question.getAnswer())
                .day(question.getDay())
                .skillId(question.getSkill().getId())
                .skillName(question.getSkill().getName())
                .bookmark(question.getUserQuestions().getFirst().isBookmark())
                .status(QuestionStatus.IN_PROGRESS)
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build();
    }

    public static UserQuestionDto fromUserQuestion(UserQuestion userQuestion) {
        return UserQuestionDto.builder()
                .questionId(userQuestion.getQuestion().getId())
                .question(userQuestion.getQuestion().getQuestion())
                .answer(userQuestion.getQuestion().getAnswer())
                .day(userQuestion.getQuestion().getDay())
                .skillId(userQuestion.getQuestion().getSkill().getId())
                .skillName(userQuestion.getQuestion().getSkill().getName())
                .bookmark(userQuestion.isBookmark())
                .status(userQuestion.getStatus())
                .createdAt(userQuestion.getCreatedAt())
                .updatedAt(userQuestion.getUpdatedAt())
                .build();
    }
}
