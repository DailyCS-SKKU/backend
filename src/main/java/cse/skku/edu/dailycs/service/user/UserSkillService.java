package cse.skku.edu.dailycs.service.user;

import cse.skku.edu.dailycs.dto.skill.SkillDto;
import cse.skku.edu.dailycs.entity.Skill;
import cse.skku.edu.dailycs.entity.User;
import cse.skku.edu.dailycs.entity.UserSkillInterest;
import cse.skku.edu.dailycs.repository.SkillRepository;
import cse.skku.edu.dailycs.repository.UserRepository;
import cse.skku.edu.dailycs.repository.UserSkillInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RelationNotification;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSkillService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillInterestRepository userSkillInterestRepository;

    public List<SkillDto> getUserSkillInterests(Long userId) {
        List<UserSkillInterest> skillInterestList = userSkillInterestRepository.findByUserId(userId);

        return skillInterestList.stream()
                .map(item -> {
                    Skill skill = item.getSkill();
                    return SkillDto.builder()
                            .id(skill.getId())
                            .name(skill.getName())
                            .build();
                })
                .toList();
    }

    public void addUserSkillInterest(Long userId, List<Long> skillId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        for (Long id : skillId) {
            Skill skill = skillRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));

            try {
                userSkillInterestRepository.save(new UserSkillInterest(user, skill));
            } catch (Exception e) {
                throw new RuntimeException("Skill already added");
            }

        }
    }

    public void deleteUserSkillInterest(Long userId, List<Long> skillId) {
        for (Long id : skillId) {
            try {
                userSkillInterestRepository.deleteByUserIdAndSkillId(userId, id);
            } catch (Exception e) {
                throw new RuntimeException("Skill not found with id: " + id);
            }
        }
    }

}
