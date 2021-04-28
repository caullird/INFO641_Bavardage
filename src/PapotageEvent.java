import java.util.EventObject;

public class PapotageEvent extends EventObject {
    public String subject;
    public String body;

    public PapotageEvent(Object source, String subject, String body) {
        super(source);
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }



}