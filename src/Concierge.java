import java.util.ArrayList;

public class Concierge {
    private ArrayList<PapotageListener> papotageListeners;
    private InterfaceGestionnaire ig;

    public ArrayList<PapotageListener> getPapotageListeners() {
        return papotageListeners;
    }

    public Concierge() {
        this.papotageListeners = new ArrayList<>();
    }

    public void addPapotageListener(PapotageListener listener) {
        this.papotageListeners.add(listener);
    }

    public Bavard newBavard(String nom) {
        Bavard b = new Bavard(nom, this);
        this.addPapotageListener(b);
        return b;
    }

    public void sendMessage(PapotageEvent papotageEvent) {
        for (PapotageListener papotageListener : papotageListeners) {
            papotageListener.receiveMessage(papotageEvent);
        }
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire unIg){
        this.ig = unIg;
    }
}
