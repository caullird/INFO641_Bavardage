public class MainTest {
    public static void main(String[] args) {
        Concierge concierge = new Concierge();
        Bavard bv1 = concierge.newBavard("bv1");
        Bavard bv2 = concierge.newBavard("bv2");
        bv1.sendMessage("aze","aezaez");
        bv2.sendMessage("aze","aezaez");

        InterfaceGestionnaire i = new InterfaceGestionnaire(concierge);

    }
}
