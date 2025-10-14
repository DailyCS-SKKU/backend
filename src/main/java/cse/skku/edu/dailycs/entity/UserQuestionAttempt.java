package cse.skku.edu.dailycs.entity;


import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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
    private int attemptNo = 1;

    @Column(nullable = false, length = 20)
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
}
