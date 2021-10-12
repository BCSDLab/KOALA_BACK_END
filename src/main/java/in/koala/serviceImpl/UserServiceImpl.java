package in.koala.serviceImpl;

import in.koala.domain.User;
import in.koala.enums.ErrorMessage;
import in.koala.exception.NonCriticalException;
import in.koala.mapper.UserMapper;
import in.koala.service.SnsLoginService;
import in.koala.service.UserService;
import in.koala.serviceImpl.sns.GoogleLogin;
import in.koala.serviceImpl.sns.KakaoLogin;
import in.koala.serviceImpl.sns.NaverLogin;
import in.koala.util.Jwt;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final HttpServletResponse response;
    private final Jwt jwt;

    @Value("${spring.jwt.access-token}")
    private String accessToken;

    @Value("${spring.jwt.refresh-token}")
    private String refreshToken;

    @Override
    public String test() {
        return userMapper.test();
    }

    // snstype을 열거형으로 바꾸기
    // oauth를 user와 분리하여 따로 controller와 service 만드는 것도 고려
    @Override
    public Map<String, String> snsLogin(String code, String snsType) throws Exception {
        // sns 별 인터페이스 구현체 변경
        SnsLoginService snsLogin = initSnsService(snsType);

        // SnsLoginService 클래스에 유저 정보요청
        Map<String, String> parsedProfile = snsLogin.requestUserProfile(code);

        User snsUser = User.builder()
                .account(parsedProfile.get("account"))
                .sns_email(parsedProfile.get("sns_email"))
                .profile(parsedProfile.get("profile"))
                .nickname(parsedProfile.get("nickname"))
                .is_auth((short) 1)
                .build();

        Long id = userMapper.getIdByAccount(snsUser.getAccount());

        // 해당 유저가 처음 sns 로그인을 요청한다면 회원가입
        if(id == null) userMapper.snsSignUp(snsUser);

        return generateToken(id);
    }

    // sns 별 oauth2 로그인 요청을 하는 메서드, 해당 api 요청한 페이지를 redirect 시킨다. swagger 에서는 작동하지 않는다. 앱에서 작동여부도 확인해봐야 함
    @Override
    public void requestSnsLogin(String snsType) throws Exception {
        SnsLoginService snsLogin = initSnsService(snsType);

        //System.out.println(uri);
        response.sendRedirect(snsLogin.getRedirectUri());
    }

    @Override
    public User signUp(User user) {
        User selectUser = userMapper.getUserByAccount(user.getAccount());

        // 해당 계정명이 이미 존재한다면 예외처리
        if(selectUser != null) throw new NonCriticalException(ErrorMessage.ACCOUNT_ALREADY_EXIST);

        // 해당 닉네임이 이미 존재한다면 예외처리
        if(userMapper.checkNickname(user.getNickname()) >= 1) throw new NonCriticalException(ErrorMessage.NICKNAME_ALREADY_EXIST);

        // 비밀번호 단방향 암호화
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        
        userMapper.signUp(user);

        return userMapper.getUserById(user.getId());
    }

    @Override
    public Map<String, String> login(User user) {
        User loginUser = userMapper.getUserByAccount(user.getAccount());

        // 해당 계정이 존재하지 않는다면 예외처리
        if(loginUser == null) throw new NonCriticalException(ErrorMessage.ACCOUNT_NOT_EXIST);

        // 계정은 존재하나 비밀번호가 존재하지 않는다면 예외처리
        if(!BCrypt.checkpw(user.getPassword(), loginUser.getPassword())) throw new NonCriticalException(ErrorMessage.WRONG_PASSWORD_EXCEPTION);

        return generateToken(loginUser.getId());
    }

    @Override
    public User getMyInfo() {
        Long id = getLoginUserId();

        User user = userMapper.getUserById(id);

        if(user == null) throw new NonCriticalException(ErrorMessage.USER_NOT_EXIST);

        return user;
    }


    private Long getLoginUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        return null;
    }

    private Map<String, String> generateToken(Long id){
        Map<String, String> token = new HashMap<>();

        token.put("access_token", jwt.generateToken(id, accessToken));
        token.put("refresh_token", jwt.generateToken(id, refreshToken));

        return token;
    }

    private SnsLoginService initSnsService(String snsType){
        //sns별 헤더 생성
        if(snsType.equals("naver")){
            return new NaverLogin();

        } else if(snsType.equals("kakao")){
            return new KakaoLogin();

        } else if(snsType.equals("google")){
            return new GoogleLogin();
        } else{
            throw new NonCriticalException(ErrorMessage.SNSTYPE_NOT_VALID);
        }
    }
}