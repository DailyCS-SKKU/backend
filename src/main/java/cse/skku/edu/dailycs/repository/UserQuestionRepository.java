package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.UserQuestion;
import cse.skku.edu.dailycs.entity.id.UserQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, UserQuestionId> {
    boolean existsByUserIdAndQuestionId(Long userId, Long questionId);

}
