package in.koala.domain;

import lombok.Getter;

@Getter
public class KeywordSetting {

    private Long userId;
    private Integer keywordNotificationOff;
    private Integer keywordVibrationOff;
    private String day;
    private String time;
    private String sound;
}
