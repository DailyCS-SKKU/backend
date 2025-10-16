package cse.skku.edu.dailycs.controller.job;

import cse.skku.edu.dailycs.dto.job.JobDto;
import cse.skku.edu.dailycs.service.job.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @RequestMapping("/all")
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }
}
