package in.koala.controller.dto;

import in.koala.domain.KeywordSetting;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeywordSettingRequest {

    private Integer keywordNotificationOff;
    private Integer keywordVibrationOff;
    private String day;
    private String time;
    private String sound;

    public KeywordSetting toKeywordSetting(Long userId){
        return KeywordSetting.builder()
                .userId(userId)
                .keywordNotificationOff(this.keywordNotificationOff)
                .keywordVibrationOff(this.keywordVibrationOff)
                .day(this.day)
                .time(this.time)
                .sound(this.sound)
                .build();
    }

}
