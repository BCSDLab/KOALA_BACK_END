package in.koala.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface KeywordSettingMapper {

    void setUserIdInKeywordSetting(Long userId);
    Integer hasDuplicatedUserIdInKeywordSetting(Long userId);
}
