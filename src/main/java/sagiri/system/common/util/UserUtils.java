package sagiri.system.common.util;

import artoria.user.UserInfo;

import static artoria.common.Constants.SYSTEM;

public class UserUtils {

    public static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(SYSTEM);
        return userInfo;
    }

}
