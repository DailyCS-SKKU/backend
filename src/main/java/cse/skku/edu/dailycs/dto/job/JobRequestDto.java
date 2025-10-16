package cse.skku.edu.dailycs.dto.job;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobRequestDto {
    List<Long> jobIds;
}
