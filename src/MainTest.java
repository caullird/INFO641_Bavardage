public class MainTest {
    public static void main(String[] args) {
        Concierge concierge = new Concierge();
        Bavard bv1 = concierge.newBavard("bv1");
        Bavard bv2 = concierge.newBavard("bv2");

        InterfaceGestionnaire i = new InterfaceGestionnaire(concierge);
    }
}
