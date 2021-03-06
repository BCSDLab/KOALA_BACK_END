package in.koala.domain.sns.googleLogin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleProfile {
    private String id;
    private String email;
    private String nickname;
    private String profile_image;

    @Builder
    public GoogleProfile(String id, String email, String nickname, String profile_image) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profile_image = profile_image;
    }
}
