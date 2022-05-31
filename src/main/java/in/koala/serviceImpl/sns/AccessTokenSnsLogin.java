package in.koala.serviceImpl.sns;

import in.koala.domain.sns.SnsUser;
import in.koala.enums.ErrorMessage;
import in.koala.exception.NonCriticalException;
import in.koala.exception.SnsLoginException;
import in.koala.service.sns.SnsLogin;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

// 인터페이스 구현의 중복을 제거하기 위해 생성한 SnsLogin 인터페이스를 상속받는 abstract 클래스
public abstract class AccessTokenSnsLogin implements SnsLogin {

    protected SnsUser requestUserProfileByAccessToken(String accessToken, String profileUri) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate rt = new RestTemplate();

        if(accessToken.charAt(0) == '"'){
            accessToken = accessToken.substring(1, accessToken.length() - 1);
        }

        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = rt.exchange(
                profileUri,
                HttpMethod.GET,
                request,
                String.class
        );

        SnsUser snsUser = this.profileParsing(response);
        if(snsUser.getAccount() == null || snsUser.getEmail() == null || snsUser.getNickname() == null){
            throw new NonCriticalException(ErrorMessage.PROFILE_SCOPE_EXCEPTION);
        }

        return snsUser;

    }

    protected String requestAccessToken(String code, String accessTokenUri) {
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> token;

        token = rt.exchange(
                accessTokenUri,
                HttpMethod.POST,
                this.getAccessTokenRequestHttpEntity(code),
                String.class
        );

        String accessToken = null;

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(token.getBody());

            accessToken = jsonObject.get("access_token").toString();

        } catch (ParseException e) {
            throw new SnsLoginException(ErrorMessage.NAVER_LOGIN_EXCEPTION);
        }

        return accessToken;
    }

    abstract SnsUser profileParsing(ResponseEntity<String> response) throws Exception;
    abstract HttpEntity getAccessTokenRequestHttpEntity(String code);
}
