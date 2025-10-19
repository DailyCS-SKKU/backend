package cse.skku.edu.dailycs.entity;


import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_question_attempts")
public class UserQuestionAttempt {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private int attemptNo = 0;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private QuestionStatus status = QuestionStatus.IN_PROGRESS; // IN_PROGRESS, CORRECT, WRONG

    @Column(columnDefinition = "TEXT")
    private String finalUserAnswer;

    @Column(columnDefinition = "TEXT")
    private String finalAiFeedback;

    @Column(columnDefinition = "TEXT")
    private String finalAiSummary;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserQuestionAttempt(User user, Question question) {
        this.user = user;
        this.question = question;
    }

    public void update(String userAnswer, String feedback, String summary, QuestionStatus status) {
        this.attemptNo += 1;
        this.finalUserAnswer = userAnswer;
        this.finalAiFeedback = feedback;
        this.finalAiSummary = summary;
    }
}
