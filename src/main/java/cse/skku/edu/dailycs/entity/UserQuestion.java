package cse.skku.edu.dailycs.entity;

import cse.skku.edu.dailycs.entity.id.UserQuestionId;
import cse.skku.edu.dailycs.util.enumType.QuestionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_questions")
@IdClass(UserQuestionId.class)
public class UserQuestion  {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private boolean bookmark = false;

    @Column(nullable = false, length = 20)
    private QuestionStatus status = QuestionStatus.IN_PROGRESS; // IN_PROGRESS, CORRECT, WRONG

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
