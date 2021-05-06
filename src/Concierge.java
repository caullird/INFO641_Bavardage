import java.util.ArrayList;

public class Concierge {
    private ArrayList<PapotageListener> papotageListeners;
    private InterfaceGestionnaire ig;

    public Concierge() {
        this.papotageListeners = new ArrayList<>();
    }

    public void addPapotageListener(PapotageListener listener) {
        this.papotageListeners.add(listener);
    }

    public void newBavard(String nom) {
        Bavard b = new Bavard(nom, this);
        this.addPapotageListener(b);
    }

    public void sendMessage(PapotageEvent papotageEvent , Bavard requester, Bavard receiver) {
        for (PapotageListener papotageListener : papotageListeners) {
            papotageListener.receiveMessage(papotageEvent);
        }
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire unIg){
        this.ig = unIg;
    }
}
