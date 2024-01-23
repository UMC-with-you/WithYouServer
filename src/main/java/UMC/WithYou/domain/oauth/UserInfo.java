package UMC.WithYou.domain.oauth;

import java.util.Map;

public interface UserInfo {
    String getEmail();
    String getName();
    String getPhoneNumber();
    Map<String, Object> toAttributes();
}
