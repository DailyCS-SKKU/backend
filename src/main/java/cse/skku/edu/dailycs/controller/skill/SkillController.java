package cse.skku.edu.dailycs.controller.skill;

import cse.skku.edu.dailycs.dto.skill.SkillDto;
import cse.skku.edu.dailycs.service.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @RequestMapping("/all")
    public List<SkillDto> getAllSkills() {
        return skillService.getAllSkills();
    }
}
