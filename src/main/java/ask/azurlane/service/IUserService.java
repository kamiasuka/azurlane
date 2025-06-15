package ask.azurlane.service;

import ask.azurlane.commom.pojo.dto.UserLoginInfoDTO;
import ask.azurlane.commom.pojo.vo.LoginResultVO;
import ask.azurlane.commom.pojo.vo.UserVO;

public interface IUserService {
    LoginResultVO Login(UserLoginInfoDTO userLoginInfoDTO);
}
