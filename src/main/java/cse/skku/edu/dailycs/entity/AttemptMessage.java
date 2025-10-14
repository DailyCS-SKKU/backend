package cse.skku.edu.dailycs.entity;

import cse.skku.edu.dailycs.util.enumType.MessageRole;
import cse.skku.edu.dailycs.util.enumType.MessageStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "attempt_messages")
public class AttemptMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attempt_id")
    private UserQuestionAttempt attempt;

    @Column(nullable = false)
    private int turnNo;

    @Column(nullable = false, length = 20)
    private MessageRole role; // user/assistant

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private MessageStatus status; // assistant/correct/wrong

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
