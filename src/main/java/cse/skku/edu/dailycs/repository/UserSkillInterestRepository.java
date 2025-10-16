package cse.skku.edu.dailycs.repository;

import cse.skku.edu.dailycs.entity.UserSkillInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillInterestRepository extends JpaRepository<UserSkillInterest, Long> {

    List<UserSkillInterest> findByUserId(Long userId);
    void deleteByUserIdAndSkillId(Long userId, Long skillId);
}
