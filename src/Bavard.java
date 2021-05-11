import java.util.ArrayList;

public class Bavard implements PapotageListener {

    private String name;
    private Concierge concierge;
    private Boolean connected;
    private InterfaceBavard iB = null;

    public Bavard(String name, Concierge concierge) {
        this.name = name;
        this.concierge = concierge;
        this.connected = false;
    }

    public InterfaceBavard getiB() {
        return iB;
    }

    public void setiB(InterfaceBavard iB) {
        this.iB = iB;
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

    public void sendMessageOne(String subject, String body, PapotageListener receiver, Bavard requestor) {
        PapotageEvent papotageEvent = new PapotageEvent(this,subject,body);
        this.concierge.sendMessageOne(papotageEvent,receiver,requestor);
    }

    public void sendMessageAll(String subject, String body){

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
