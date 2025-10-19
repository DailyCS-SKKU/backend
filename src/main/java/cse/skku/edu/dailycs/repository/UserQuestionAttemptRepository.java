package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.UserQuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserQuestionAttemptRepository extends JpaRepository<UserQuestionAttempt, Long> {


    boolean existsByUserIdAndQuestionId(Long userId, Long questionId);

    Optional<UserQuestionAttempt> findByUserIdAndQuestionId(Long userId, Long questionId);
}
