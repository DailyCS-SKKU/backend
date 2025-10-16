package cse.skku.edu.dailycs.controller.user;

import cse.skku.edu.dailycs.dto.job.JobDto;
import cse.skku.edu.dailycs.dto.job.JobRequestDto;
import cse.skku.edu.dailycs.dto.skill.SkillDto;
import cse.skku.edu.dailycs.dto.skill.SkillRequestDto;
import cse.skku.edu.dailycs.dto.user.UserInfoDto;
import cse.skku.edu.dailycs.service.auth.JwtService;
import cse.skku.edu.dailycs.service.user.UserInfoService;
import cse.skku.edu.dailycs.service.user.UserJobService;
import cse.skku.edu.dailycs.service.user.UserSkillService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final JwtService jwtService;
    private final UserSkillService userSkillService;
    private final UserJobService userJobService;
    private final UserInfoService userInfoService;

    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getUserInfo(
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        return ResponseEntity.ok().body(userInfoService.getUserInfoByUserId(userId));
    }


    @GetMapping("/get-skill")
    public ResponseEntity<List<SkillDto>> getSkill(
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        List<SkillDto> skillDtoList = userSkillService.getUserSkillInterests(userId);

        return ResponseEntity.ok().body(skillDtoList);
    }


    @PostMapping("/add-skill")
    public ResponseEntity<Void> addSkill(
            HttpServletRequest request,
            @RequestBody SkillRequestDto skillRequestDto
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        userSkillService.addUserSkillInterest(userId, skillRequestDto.getSkillIds());

        return ResponseEntity.ok().body(null);
    }


    @DeleteMapping("/delete-skill")
    public ResponseEntity<Void> deleteSkill(
            HttpServletRequest request,
            @RequestBody SkillRequestDto skillRequestDto
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);
        userSkillService.deleteUserSkillInterest(userId, skillRequestDto.getSkillIds());

        return ResponseEntity.ok().body(null);
    }


    @GetMapping("/get-job")
    public ResponseEntity<List<JobDto>> getJob(
            HttpServletRequest request
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        List<JobDto> jobDtoList = userJobService.getUserJobInterests(userId);

        return ResponseEntity.ok().body(jobDtoList);
    }


    @PostMapping("/add-job")
    public ResponseEntity<Void> addJob(
            HttpServletRequest request,
            @RequestBody JobRequestDto jobRequestDto
            ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        userJobService.addUserJobInterest(userId, jobRequestDto.getJobIds());

        return ResponseEntity.ok().body(null);
    }


    @DeleteMapping("/delete-job")
    public ResponseEntity<Void> deleteJob(
            HttpServletRequest request,
            @RequestBody JobRequestDto jobRequestDto
    ) {
        String token = jwtService.parseJWT(request);
        Long userId = jwtService.getUserId(token);

        userJobService.deleteUserJobInterest(userId, jobRequestDto.getJobIds());

        return ResponseEntity.ok().body(null);
    }
}
