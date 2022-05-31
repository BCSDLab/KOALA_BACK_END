package in.koala.serviceImpl.sns;

import in.koala.domain.sns.SnsUser;
import in.koala.enums.ErrorMessage;
import in.koala.enums.SnsType;
import in.koala.exception.NonCriticalException;
import in.koala.exception.SnsLoginException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class KakaoLogin extends AccessTokenSnsLogin {
    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.access-token-uri}")
    private String accessTokenUri;

    @Value("${kakao.profile-uri}")
    private String profileUri;

    @Value("${kakao.login-request-uri}")
    private String loginRequestUri;

    @Override
    public SnsUser requestUserProfileByToken(String token) {
        try {
            return this.requestUserProfileByAccessToken(token, profileUri);

        } catch(Exception e){
            throw new SnsLoginException(ErrorMessage.KAKAO_LOGIN_EXCEPTION);
        }
    }

    @Override
    public SnsType getSnsType() {
        return SnsType.KAKAO;
    }

    @Override
    public SnsUser profileParsing(ResponseEntity<String> response) {
        SnsUser snsUser = null;

        try{
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
            JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");
            JSONObject profile = (JSONObject) kakaoAccount.get("profile");

            snsUser = SnsUser.builder()
                    .account(this.getSnsType() + "_" + jsonObject.get("id"))
                    .email(kakaoAccount.get("email").toString())
                    .nickname(this.getSnsType() + "_" + jsonObject.get("id"))
                    .profile((String) profile.get("profile_image_url"))
                    .snsType(SnsType.KAKAO)
                    .build();

        } catch (ParseException e) {
            throw new SnsLoginException(ErrorMessage.KAKAO_LOGIN_EXCEPTION);
        }

        return snsUser;
    }

    @Override
    public HttpEntity getAccessTokenRequestHttpEntity(String code) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);

        return new HttpEntity<>(params, headers);
    }
}
