package UMC.WithYou.domain.auth;

import java.util.Map;

public class GoogleUserInfo implements UserInfo {
    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public Map<String, Object> toAttributes() {
        return attributes;
    }
}
