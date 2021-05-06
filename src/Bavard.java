import java.util.ArrayList;

public class Bavard implements PapotageListener {

    private String name;
    private Concierge concierge;

    public Bavard(String name, Concierge concierge) {
        this.name = name;
        this.concierge = concierge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

    public void sendMessage(String subject, String body) {
        PapotageEvent papotageEvent = new PapotageEvent(this,subject,body);
        this.concierge.sendMessage(papotageEvent);
    }

    public void receiveMessage(PapotageEvent papotageEvent) {
        System.out.println(papotageEvent);
    }

    @Override
    public String toString() {
        return name;
    }
}