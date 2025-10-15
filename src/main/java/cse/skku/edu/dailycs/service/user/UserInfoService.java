package cse.skku.edu.dailycs.service.user;

import cse.skku.edu.dailycs.dto.user.UserInfoDto;
import cse.skku.edu.dailycs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    private final UserRepository userRepository;

    public UserInfoDto getUserInfoByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(UserInfoDto::fromUser)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
