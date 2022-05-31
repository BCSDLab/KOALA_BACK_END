package in.koala.service;

import in.koala.domain.AuthEmail;
import in.koala.domain.JWToken;
import in.koala.domain.user.NormalUser;
import in.koala.domain.user.User;
import in.koala.enums.EmailType;
import in.koala.enums.SnsType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {
    JWToken snsSingIn(SnsType snsType, String deviceToken);
    JWToken login(NormalUser user, String deviceToken);
    NormalUser signUp(NormalUser user);
    User getLoginUserInfo();
    NormalUser getLoginNormalUserInfo();
    void updateNickname(String nickname);
    Boolean checkNickNameDuplicated(String nickname);
    Boolean checkAccount(String account);
    JWToken refresh();
    void sendEmail(AuthEmail authEmail, EmailType emailType);
    void certificateEmail(AuthEmail authEmail, EmailType emailType);
    boolean checkUniversityCertification();
    void changePassword(NormalUser user);
    String findAccount(String email);
    void softDeleteUser();
    Boolean checkFindEmailDuplicated(String email);
    String editProfile(MultipartFile multipartFile);
    JWToken getSocketToken();
    JWToken nonMemberLogin(String deviceToken);
}
