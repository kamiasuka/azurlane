package ask.azurlane.controller;

import ask.azurlane.commom.pojo.dto.UserLoginInfoDTO;
import ask.azurlane.commom.pojo.vo.LoginResultVO;
import ask.azurlane.commom.response.JsonResult;
import ask.azurlane.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/al/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/test")
    public JsonResult test()
    {
        return JsonResult.ok("hello");
    }

    @PostMapping("/login")
    public JsonResult login(UserLoginInfoDTO userLoginInfoDTO) {
        LoginResultVO loginResultVO = iUserService.Login(userLoginInfoDTO);
        return JsonResult.ok(loginResultVO);
    }

}
