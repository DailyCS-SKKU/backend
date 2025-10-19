package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.dto.question.UserQuestionDto;
import cse.skku.edu.dailycs.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("""
            SELECT new cse.skku.edu.dailycs.dto.question.UserQuestionDto(q, up)
            FROM Question q
            LEFT JOIN q.userQuestions up
                   ON up.user.id = :userId
            WHERE q.skill.id = :skillId
            """)
    List<UserQuestionDto> findAllQuestionsByUserIdAndSkillId(
            @Param("skillId") Long skillId,
            @Param("userId") Long userId);

}
