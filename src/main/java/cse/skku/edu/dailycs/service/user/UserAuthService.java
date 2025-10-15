package cse.skku.edu.dailycs.service.user;

import cse.skku.edu.dailycs.controller.user.UserInfoDto;
import cse.skku.edu.dailycs.dto.auth.LoginUserDto;
import cse.skku.edu.dailycs.entity.User;
import cse.skku.edu.dailycs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;

    public UserInfoDto createUserIfNotExists(LoginUserDto loginUserDto) {
        System.out.println("LoginUserDto: " + loginUserDto.getName() + ", " + loginUserDto.getEmail() + ", " + loginUserDto.getProvider() + ", " + loginUserDto.getImageUrl());

        User user = userRepository.findByEmail(loginUserDto.getEmail());
        if (user == null) {
            user = new User(loginUserDto);
            userRepository.save(user);
        }

        return UserInfoDto.fromUser(user);
    }
}
