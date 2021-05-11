import java.util.ArrayList;

public class Concierge {
    private ArrayList<Bavard> bavards;
    private ArrayList<PapotageListener> papotageListeners;
    private InterfaceGestionnaire ig;

    public ArrayList<Bavard> getBavards() {
        return bavards;
    }

    public ArrayList<PapotageListener> getPapotageListeners() {
        return papotageListeners;
    }

    public InterfaceGestionnaire getIg() {
        return ig;
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

    public void sendMessage(PapotageEvent papotageEvent) {
        for (PapotageListener papotageListener : papotageListeners) {
            papotageListener.receiveMessage(papotageEvent);
        }
    }

    public void bavardSignIn(String name) {
        for (Bavard bavard : this.getBavards()) {
            if (bavard.getName().equals(name)) {
                bavard.setConnected(true);
                this.addPapotageListener(bavard);
                System.out.println("Bavard " + name + " connected");
                this.getIg().updateConnectedBavard(bavards);
                return;
            }
        }
        // @TODO display error : user don't exist
        System.out.println("error : user don't exist");
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire unIg){
        this.ig = unIg;
    }
}
