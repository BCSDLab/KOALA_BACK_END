package in.koala.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class KeywordSetting {

    private Long userId;
    private Integer keywordNotificationOff;
    private Integer keywordVibrationOff;
    private String day;
    private String time;
    private String sound;

    @Builder
    public KeywordSetting(Long userId, Integer keywordNotificationOff, Integer keywordVibrationOff,
                          String day, String time, String sound){
        this.userId = userId;
        this.keywordNotificationOff = keywordNotificationOff;
        this.keywordVibrationOff = keywordVibrationOff;
        this.day = day;
        this.time = time;
        this.sound = sound;
    }

}
