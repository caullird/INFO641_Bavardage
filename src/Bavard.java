import java.util.ArrayList;

public class Bavard implements PapotageListener {

    private ArrayList<PapotageListener> bavards_ban;
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

    @Override
    public void responsePrivateMessage(String subject, String body, PapotageListener requestor, PapotageListener receiver, InterfacePrivateMessage iMpRequestor, InterfacePrivateMessage iMpReceiver) {
        PapotageEvent unPapotageEvent = new PapotageEvent(this, subject,body);
        this.concierge.createMPReponse(unPapotageEvent,receiver,requestor,iMpRequestor,iMpReceiver);
    }


    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public void createMessagePrivate(String subject, String body, PapotageListener receiver, Bavard requestor) {
        PapotageEvent unPapotageEvent = new PapotageEvent(this,subject,body);
        this.concierge.createMessagePrivate(unPapotageEvent,receiver,requestor);
    }



    public void sendMessageAll(String subject, String body, Bavard requestor){
        PapotageEvent unPapotageEvent = new PapotageEvent(this,subject,body);
        this.concierge.sendMessageAll(unPapotageEvent,requestor);
    }

    public void receiveMessage(PapotageEvent papotageEvent) {
        System.out.println(papotageEvent);
    }


    @Override
    public String toString() {
        return name;
    }
}
