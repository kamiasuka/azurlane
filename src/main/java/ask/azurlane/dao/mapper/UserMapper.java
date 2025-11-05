package ask.azurlane.dao.mapper;

import ask.azurlane.commom.pojo.dto.UserInfoDTO;
import ask.azurlane.commom.pojo.dto.UserLoginInfoDTO;
import ask.azurlane.commom.pojo.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    int addUser(UserInfoDTO userInfoDTO);

    UserVO getInfoByUsername(String username);

    int removeUser(UserInfoDTO userInfoDTO);
}
