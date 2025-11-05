package ask.azurlane.service.impl;

import ask.azurlane.commom.exception.ServiceException;
import ask.azurlane.commom.jwt.JwtUtils;
import ask.azurlane.commom.pojo.dto.UserInfoDTO;
import ask.azurlane.commom.pojo.dto.UserLoginInfoDTO;
import ask.azurlane.commom.pojo.vo.LoginResultVO;
import ask.azurlane.commom.pojo.vo.UserVO;
import ask.azurlane.commom.response.JsonResult;
import ask.azurlane.commom.response.StatusCode;
import ask.azurlane.dao.mapper.UserMapper;
import ask.azurlane.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登陆校验
     * @param userLoginInfoDTO
     * @return
     */
    @Override
    public LoginResultVO Login(UserLoginInfoDTO userLoginInfoDTO) {
        UserVO userVO = userMapper.getInfoByUsername(userLoginInfoDTO.getUsername());
        if (userVO == null)
            throw new ServiceException(StatusCode.USERNAME_ERROR);

        String password = userVO.getPassword();
        String md5PassWord = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5PassWord.equals(userLoginInfoDTO.getPassword()))
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        LoginResultVO loginResultVO = new LoginResultVO();
        BeanUtils.copyProperties(userVO,loginResultVO);
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userVO.getId());
        claims.put("username",userVO.getUsername());
        claims.put("identity",userVO.getIdentity());
        String JWT = JwtUtils.generateJwt(claims);
        loginResultVO.setJwt(JWT);
        return loginResultVO;
    }

    @Override
    public int addUser(UserInfoDTO userInfoDTO) {
        String md5pd = userInfoDTO.getPassword();
        userInfoDTO.setPassword(DigestUtils.md5DigestAsHex(md5pd.getBytes()));
        return userMapper.addUser(userInfoDTO);
    }

    @Override
    public int removeUser(UserInfoDTO userInfoDTO) {
        return userMapper.removeUser(userInfoDTO);
    }
}
