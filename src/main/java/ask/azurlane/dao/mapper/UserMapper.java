package ask.azurlane.dao.mapper;

import ask.azurlane.commom.pojo.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserVO getInfoByUsername(String username);
}
