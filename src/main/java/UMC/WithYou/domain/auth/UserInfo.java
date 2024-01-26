package UMC.WithYou.domain.auth;

import java.util.Map;

public interface UserInfo {
    String getEmail();
    String getName();
    String getPhoneNumber();
    Map<String, Object> toAttributes();
}
