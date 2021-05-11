import java.util.ArrayList;

public class Bavard implements PapotageListener {

    private String name;
    private Concierge concierge;
    private Boolean connected;

    public Bavard(String name, Concierge concierge) {
        this.name = name;
        this.concierge = concierge;
        this.connected = false;
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

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public void sendMessage(String subject, String body) {
        PapotageEvent papotageEvent = new PapotageEvent(this,subject,body);
        this.concierge.sendMessage(papotageEvent);
    }

    public void receiveMessage(PapotageEvent papotageEvent) {
        // Display message
        System.out.println(papotageEvent);
    }

    @Override
    public String toString() {
        return name;
    }
}
