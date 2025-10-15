package cse.skku.edu.dailycs.entity;

import cse.skku.edu.dailycs.entity.id.StudyGroupMemberId;
import cse.skku.edu.dailycs.util.enumType.StudyGroupRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "study_group_members")
@IdClass(StudyGroupMemberId.class)
public class StudyGroupMember {

    @Id @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @Id @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private StudyGroupRole role;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime joinedAt;
}
