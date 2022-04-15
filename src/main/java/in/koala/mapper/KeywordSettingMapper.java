package in.koala.mapper;

import in.koala.domain.KeywordSetting;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordSettingMapper {

    void setUserIdInKeywordSetting(Long userId);
    Integer hasDuplicatedUserIdInKeywordSetting(Long userId);
    KeywordSetting getUserKeywordSettingByUserId(Long userId);
    void modifyKeywordSetting(KeywordSetting keywordSetting);
}
