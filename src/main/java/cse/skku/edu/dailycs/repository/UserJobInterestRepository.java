package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.UserJobInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJobInterestRepository extends JpaRepository<UserJobInterest, Long> {

    List<UserJobInterest> findByUserId(Long userId);
    void deleteByUserIdAndJobId(Long userId, Long jobId);
}
