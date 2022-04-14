package in.koala.serviceImpl;

import in.koala.mapper.KeywordSettingMapper;
import in.koala.service.KeywordSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeywordSettingServiceImpl implements KeywordSettingService {

    private final KeywordSettingMapper keywordSettingMapper;

    @Override
    @Transactional
    public void setUserIdInKeywordSetting(Long userId) {

        if(!hasDuplicatedUserIdInKeywordSetting(userId))
            keywordSettingMapper.setUserIdInKeywordSetting(userId);
    }

    private Boolean hasDuplicatedUserIdInKeywordSetting(Long userId){
        if(keywordSettingMapper.hasDuplicatedUserIdInKeywordSetting(userId)>0)
            return true;

        return false;
    }
}
