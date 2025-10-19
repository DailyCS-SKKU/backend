package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.AttemptMessage;
import cse.skku.edu.dailycs.entity.UserQuestionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AttemptMessageRepository extends JpaRepository<AttemptMessage, Long> {

    List<AttemptMessage> findAllByAttemptId(Long attemptId);
}
