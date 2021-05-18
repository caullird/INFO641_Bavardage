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

    public void createMessagePrivate(PapotageEvent message, PapotageListener receiver, PapotageListener requestor) {

        InterfacePrivateMessage interfaceBavardRequestor = new InterfacePrivateMessage(requestor,receiver);
        interfaceBavardRequestor.setBavardRequestor(requestor);
        interfaceBavardRequestor.setBavardReceiver(receiver);
        interfaceBavardRequestor.setVisible(true);


        InterfacePrivateMessage interfaceBavardReceiver = new InterfacePrivateMessage(receiver,requestor);
        interfaceBavardReceiver.setBavardRequestor(receiver);
        interfaceBavardReceiver.setBavardReceiver(requestor);
        interfaceBavardReceiver.setVisible(true);

        interfaceBavardRequestor.setinterfaceMPReceiver(interfaceBavardReceiver);
        interfaceBavardReceiver.setinterfaceMPReceiver(interfaceBavardRequestor);

        interfaceBavardRequestor.displayMessage(message, requestor);
        interfaceBavardReceiver.displayMessage(message, requestor);

        this.getInterfaceGestionnaire().displayMessage(message, requestor, receiver);
    }

    public void sendMessageAll(PapotageEvent message, PapotageListener requestor){
        for(PapotageListener unPapotageListener : this.getPapotageListeners()){
            if(!unPapotageListener.equals(requestor)){
                unPapotageListener.getiB().displayMessage(message,requestor);
            }
        }
        requestor.getiB().displayMessage(message,requestor);
        this.getInterfaceGestionnaire().displayMessage(message,requestor,null);

    }

    public void createMPReponse (PapotageEvent message, PapotageListener receiver, PapotageListener requestor, InterfacePrivateMessage iMpRequestor,InterfacePrivateMessage iMpReceiver){
        iMpReceiver.displayMessage(message,requestor);
        iMpRequestor.displayMessage(message,requestor);
        this.getInterfaceGestionnaire().displayMessage(message,requestor,receiver);
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
        for(PapotageListener unPapotageListener : this.getPapotageListeners()){
            unPapotageListener.getiB().displayOnlineUser();
        }

    }
}
