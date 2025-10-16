package cse.skku.edu.dailycs.service.user;

import cse.skku.edu.dailycs.dto.job.JobDto;
import cse.skku.edu.dailycs.entity.Job;
import cse.skku.edu.dailycs.entity.UserJobInterest;
import cse.skku.edu.dailycs.repository.JobRepository;
import cse.skku.edu.dailycs.repository.UserJobInterestRepository;
import cse.skku.edu.dailycs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserJobService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final UserJobInterestRepository userJobInterestRepository;

    public List<JobDto> getUserJobInterests(Long userId) {
        List<UserJobInterest> jobInterestList = userJobInterestRepository.findByUserId(userId);

        return jobInterestList.stream()
                .map(item -> {
                    Job job = item.getJob();
                    return JobDto.builder()
                            .id(job.getId())
                            .name(job.getName())
                            .build();
                })
                .toList();
    }

    public void addUserJobInterest(Long userId, List<Long> jobId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        for (Long id : jobId) {
            var job = jobRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

            try {
                userJobInterestRepository.save(new UserJobInterest(user, job));
            } catch (Exception e) {
                throw new RuntimeException("Job already added");
            }
        }
    }

    public void deleteUserJobInterest(Long userId, List<Long> jobId) {
        for (Long id : jobId) {
            try {
                userJobInterestRepository.deleteByUserIdAndJobId(userId, id);
            } catch (Exception e) {
                throw new RuntimeException("Job not found in user's interest");
            }
        }
    }
}
