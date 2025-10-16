package cse.skku.edu.dailycs.dto.job;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JobDto {

    private Long id;
    private String name;
}
