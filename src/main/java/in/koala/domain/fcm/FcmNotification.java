package in.koala.domain.fcm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FcmNotification {
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public FcmNotification(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}