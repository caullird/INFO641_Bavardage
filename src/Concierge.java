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

    public InterfaceGestionnaire getiG() {
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
        for(Bavard bavard : bavards){
            bavard.getiB().displayOnlineUser();
        }
    }

    public Bavard newBavard(String name) {
        Bavard b = new Bavard(name, this);
        this.addBavard(b);
        return b;
    }

    public void sendMessageOne(PapotageEvent message, PapotageListener receiver, PapotageListener requestor) {
        receiver.getiB().displayMessageReceived(message,requestor);
        requestor.getiB().displayMessageSend(message,receiver);
        this.getiG().displayMessage(message, requestor, receiver);
    }

    public void sendMessageAll(PapotageEvent message, PapotageListener requestor){
        for(PapotageListener unPapotageListener : this.getPapotageListeners()){
            if(!unPapotageListener.equals(requestor)){
                unPapotageListener.getiB().displayMessageReceived(message,requestor);
                requestor.getiB().displayMessageSend(message,unPapotageListener);
                this.getiG().displayMessage(message,requestor,unPapotageListener);
            }
        }

    }

    public Bavard bavardSignIn(String name, InterfaceRegister iR) {
        for (Bavard bavard : this.getBavards()) {
            if (bavard.getName().equals(name)) {
                bavard.setConnected(true);
                this.addPapotageListener(bavard);
                System.out.println("Bavard " + name + " connected");
                this.getiG().displayOnlineUser();
                iR.displayInformationMessage("Login : the user has been connected",false);
                return bavard;
            }
        }
        iR.displayInformationMessage("Error : user don't exist",true);
        return null;
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire uniG){
        this.iG = uniG;
    }

    public void bavardSignOut(Bavard bavard) {
        bavard.setConnected(false);
        this.iG.displayOnlineUser();
    }
}
