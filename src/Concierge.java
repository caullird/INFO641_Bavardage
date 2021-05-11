import java.util.ArrayList;

public class Concierge {
    private ArrayList<Bavard> bavards;
    private ArrayList<PapotageListener> papotageListeners;
    private InterfaceGestionnaire iG;

    public ArrayList<Bavard> getBavards() {
        return bavards;
    }

    public ArrayList<PapotageListener> getPapotageListeners() {
        return papotageListeners;
    }

    public InterfaceGestionnaire getIg() {
        return iG;
    }

    public Concierge() {
        this.bavards = new ArrayList<>();
        this.papotageListeners = new ArrayList<>();
    }

    public void addBavard(Bavard bavard) {
        this.bavards.add(bavard);
    }

    public void addPapotageListener(PapotageListener listener) {
        this.papotageListeners.add(listener);
    }

    public Bavard newBavard(String name) {
        Bavard b = new Bavard(name, this);
        this.addBavard(b);
        return b;
    }

    public void sendMessageOne(PapotageEvent message, PapotageListener receiver, PapotageListener requestor) {
        receiver.getiB().displayMessageReceived(message,requestor);
        requestor.getiB().displayMessageSend(message,receiver);
        iG.displayMessage(message, requestor, receiver);
    }

    public void sendMessageAll(PapotageEvent message, PapotageListener requestor){

    }

    public Bavard bavardSignIn(String name) {
        for (Bavard bavard : this.getBavards()) {
            if (bavard.getName().equals(name)) {
                bavard.setConnected(true);
                this.addPapotageListener(bavard);
                System.out.println("Bavard " + name + " connected");
                this.getIg().displayOnlineUser();
                return bavard;
            }
        }
        // @TODO display error : user don't exist
        System.out.println("error : user don't exist");
        return null;
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire unIg){
        this.iG = unIg;
    }

    public void bavardSignOut(Bavard bavard) {
        bavard.setConnected(false);
        this.iG.displayOnlineUser();
    }
}
