package cse.skku.edu.dailycs.service.job;

import cse.skku.edu.dailycs.dto.job.JobDto;
import cse.skku.edu.dailycs.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(job -> JobDto.builder()
                        .id(job.getId())
                        .name(job.getName())
                        .build())
                .toList();
    }
}
