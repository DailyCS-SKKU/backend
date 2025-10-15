package cse.skku.edu.dailycs.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    @RequestMapping("/test")
    public String test() {
        return "토큰이 있어서 성공!";
    }
}
