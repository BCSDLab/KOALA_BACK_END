package in.koala.controller.dto.user;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class EditProfileResponse {
    private String profile;

    public EditProfileResponse(String profile) {
        this.profile = profile;
    }
}
