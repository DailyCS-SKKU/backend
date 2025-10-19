package cse.skku.edu.dailycs.dto.ai;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AiRequestDto {

    private Long questionId;
    private String userAnswer;
    private String aiSummary;
}
