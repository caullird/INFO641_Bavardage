import java.util.ArrayList;

public class Concierge {
    private ArrayList<Bavard> bavards;
    private ArrayList<PapotageListener> papotageListeners;
    private InterfaceGestionnaire interfaceGestionnaire;

    public ArrayList<Bavard> getBavards() {
        return bavards;
    }

    public ArrayList<PapotageListener> getPapotageListeners() {
        return papotageListeners;
    }

    public InterfaceGestionnaire getInterfaceGestionnaire() {
        return interfaceGestionnaire;
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
        Bavard bavard = new Bavard(name, this);
        this.addBavard(bavard);
        return bavard;
    }

    public void sendMessageOne(PapotageEvent message, PapotageListener receiver, PapotageListener requestor) {
        //receiver.getiB().displayMessageReceived(message,requestor);
        //requestor.getiB().displayMessageSend(message,receiver);

        if(receiver != requestor){
            receiver.getiB().displayMessage(message, requestor, receiver);
        }

        requestor.getiB().displayMessage(message, requestor, receiver);
        this.getInterfaceGestionnaire().displayMessage(message, requestor, receiver);
    }

    public void sendMessageAll(PapotageEvent message, PapotageListener requestor){
        for(PapotageListener unPapotageListener : this.getPapotageListeners()){
            if(!unPapotageListener.equals(requestor)){
                //unPapotageListener.getiB().displayMessageReceived(message,requestor);
                //requestor.getiB().displayMessageSend(message,unPapotageListener);
                unPapotageListener.getiB().displayMessage(message,requestor,unPapotageListener);
                requestor.getiB().displayMessage(message,requestor,unPapotageListener);
                this.getInterfaceGestionnaire().displayMessage(message,requestor,unPapotageListener);
            }
        }

    }

    public Bavard bavardSignIn(String name, InterfaceRegister interfaceRegister) {
        for (Bavard bavard : this.getBavards()) {
            if (bavard.getName().equals(name)) {
                bavard.setConnected(true);
                this.addPapotageListener(bavard);
                System.out.println("Bavard " + name + " connected");
                this.getInterfaceGestionnaire().displayOnlineUser();
                interfaceRegister.displayInformationMessage("Login : the user has been connected",false);
                return bavard;
            }
        }

        interfaceRegister.displayInformationMessage("L'utilsiateur " + name + " n'existe pas",true);

        return null;
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire uniG){
        this.interfaceGestionnaire = uniG;
    }

    public void bavardSignOut(Bavard bavard) {
        bavard.setConnected(false);
        this.interfaceGestionnaire.displayOnlineUser();
    }
}
