package in.koala.mapper;

import in.koala.domain.DeviceToken;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceTokenMapper {
    void insertDeviceToken(DeviceToken deviceToken);
    List<DeviceToken> getDeviceTokenByUserId(Long userId);
    void updateUserId(DeviceToken deviceToken);
    void updateNonUserId(DeviceToken deviceToken);
    int checkTokenExist(String deviceToken);
    Optional<DeviceToken> getTokenByDeviceToken(String deviceToken);
    void updateExTokenToNewToken(String expiredToken, String newToken);
}
