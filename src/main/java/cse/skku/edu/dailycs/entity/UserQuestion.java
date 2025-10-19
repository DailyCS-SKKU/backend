package cse.skku.edu.dailycs.entity;

import cse.skku.edu.dailycs.entity.id.UserQuestionId;
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
    @Enumerated(EnumType.STRING)
    private QuestionStatus status = QuestionStatus.IN_PROGRESS; // IN_PROGRESS, CORRECT, WRONG

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserQuestion(User user, Question question) {
        this.user = user;
        this.question = question;
    }

    public void updateStatus(QuestionStatus status) {
        this.status = status;
    }
}
