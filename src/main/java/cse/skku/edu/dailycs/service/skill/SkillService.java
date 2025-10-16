package cse.skku.edu.dailycs.service.skill;

import cse.skku.edu.dailycs.dto.skill.SkillDto;
import cse.skku.edu.dailycs.entity.Skill;
import cse.skku.edu.dailycs.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public List<SkillDto> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream()
                .map(skill -> SkillDto.builder()
                        .id(skill.getId())
                        .name(skill.getName())
                        .build())
                .toList();
    }
}
