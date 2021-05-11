public interface PapotageListener {
    public void receiveMessage(PapotageEvent papotageEvent);
    public String getName();
    public InterfaceBavard getiB();
    public void setiB(InterfaceBavard iB);

    public Boolean getConnected();
}
