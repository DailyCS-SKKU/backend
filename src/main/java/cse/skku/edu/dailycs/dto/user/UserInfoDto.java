package cse.skku.edu.dailycs.dto.user;

import cse.skku.edu.dailycs.entity.User;
import cse.skku.edu.dailycs.util.enumType.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserInfoDto {

    private Long id;
    private String provider;
    private String email;
    private String name;
    private String nickname;
    private String imageUrl;
    private UserStatus status;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserInfoDto fromUser(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .provider(user.getProvider())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .imageUrl(user.getImageUrl())
                .status(user.getStatus())
                .lastLoginAt(user.getLastLoginAt())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
