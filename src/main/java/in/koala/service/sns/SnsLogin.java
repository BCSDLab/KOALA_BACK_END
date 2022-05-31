package in.koala.service.sns;

import in.koala.domain.sns.SnsUser;
import in.koala.enums.SnsType;

import java.util.Map;

public interface SnsLogin {
    SnsUser requestUserProfileByToken(String token);
    SnsType getSnsType();
}
