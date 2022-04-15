package in.koala.service;

import in.koala.domain.KeywordSetting;

public interface KeywordSettingService {

    void setUserIdInKeywordSetting(Long userId);
    KeywordSetting getUserKeywordSetting(Long userId);
}
