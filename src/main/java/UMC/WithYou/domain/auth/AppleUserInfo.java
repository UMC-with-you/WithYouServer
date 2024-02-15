package UMC.WithYou.domain.auth;

import java.util.Map;

public class AppleUserInfo implements UserInfo {
    private final Map<String, Object> attributes;

    public AppleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    // 이 메소드는 사용자의 전체 이름을 반환합니다.
    @Override
    public String getName() {
        String firstName = getFirstName();
        String lastName = getLastName();
        return (firstName != null ? firstName + " " : "") + (lastName != null ? lastName : "");
    }

    private String getFirstName() {
        Map<String, Object> nameAttributes = (Map<String, Object>) attributes.get("name");
        return nameAttributes != null ? (String) nameAttributes.get("firstName") : null;
    }

    private String getLastName() {
        Map<String, Object> nameAttributes = (Map<String, Object>) attributes.get("name");
        return nameAttributes != null ? (String) nameAttributes.get("lastName") : null;
    }

    @Override
    public Map<String, Object> toAttributes() {
        return attributes;
    }
}