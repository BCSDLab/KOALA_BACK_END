package in.koala.util;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RequiredArgsConstructor
public class SesSender {

    private final AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync;

    public void sendMail(String FROM, String TO, String SUBJECT, String HTMLBODY) {
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(
                        new Destination().withToAddresses(TO) // 받는 사람
                )
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8").withData(HTMLBODY)) // HTML 양식의 본문
                        )
                        .withSubject(new Content()
                                .withCharset("UTF-8").withData(SUBJECT)) // 제목
                )
                .withSource(FROM);  // Verify된 Email

        amazonSimpleEmailServiceAsync.sendEmailAsync(request);
    }
}
