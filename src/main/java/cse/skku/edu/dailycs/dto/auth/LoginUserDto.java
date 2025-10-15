package cse.skku.edu.dailycs.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginUserDto {

    private String name;
    private String imageUrl;
    private String email;
    private String provider;
}
