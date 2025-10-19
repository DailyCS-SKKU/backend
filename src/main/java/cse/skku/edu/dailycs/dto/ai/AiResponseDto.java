package cse.skku.edu.dailycs.dto.ai;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AiResponseDto {

    private String feedback;
    private String good;
    private String improve;
    private Integer score;
    private String summary;

}
