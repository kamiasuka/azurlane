package ask.azurlane.service.impl;

import ask.azurlane.commom.exception.ServiceException;
import ask.azurlane.commom.pojo.dto.UserLoginInfoDTO;
import ask.azurlane.commom.pojo.vo.LoginResultVO;
import ask.azurlane.commom.pojo.vo.UserVO;
import ask.azurlane.commom.response.JsonResult;
import ask.azurlane.commom.response.StatusCode;
import ask.azurlane.dao.mapper.UserMapper;
import ask.azurlane.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public LoginResultVO Login(UserLoginInfoDTO userLoginInfoDTO) {
        UserVO userVO = userMapper.getInfoByUsername(userLoginInfoDTO.getUsername());
        if (userVO == null)
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        if (!userVO.getPassword().equals(userLoginInfoDTO.getPassword()))
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        LoginResultVO loginResultVO = new LoginResultVO();
        BeanUtils.copyProperties(userVO,loginResultVO);
        return loginResultVO;
    }
}
