package in.koala.serviceImpl.sns;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import in.koala.domain.sns.SnsUser;
import in.koala.enums.ErrorMessage;
import in.koala.enums.SnsType;
import in.koala.exception.NonCriticalException;
import in.koala.exception.SnsLoginException;
import in.koala.service.sns.SnsLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GoogleLogin implements SnsLogin {

    @Value("${google.client-id}")
    private String GOOGLE_CLIENT_ID;

    @Override
    public SnsUser requestUserProfileByToken(String token) {
        GoogleIdToken claims = verifyToken(token);

        return SnsUser.builder()
                .account(getSnsType() + "_" + claims.getPayload().get("sub"))
                .email((String) claims.getPayload().get("email"))
                .nickname(getSnsType() + "_" + claims.getPayload().get("sub"))
                .profile((String) claims.getPayload().get("picture"))
                .snsType(SnsType.GOOGLE)
                .build();
    }

    private GoogleIdToken verifyToken(String idToken) {

        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                    .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
                    .build();

            // verify 는 토큰이 유효하지 않다면 null을 반환한다
            GoogleIdToken googleIdToken = verifier.verify(idToken);

            if(googleIdToken == null) {
                throw new SnsLoginException(ErrorMessage.IDENTITY_TOKEN_INVALID_EXCEPTION);
            }

            return googleIdToken;

        } catch (Exception e){
            throw new SnsLoginException(ErrorMessage.SNS_LOGIN_EXCEPTION);
        }
    }

    @Override
    public SnsType getSnsType() {
        return SnsType.GOOGLE;
    }
}
