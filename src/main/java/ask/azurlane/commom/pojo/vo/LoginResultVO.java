package ask.azurlane.commom.pojo.vo;

import lombok.Data;

@Data
public class LoginResultVO {
    private long id;
    private String username;
    private String nickname;
    private String jwt;
}
