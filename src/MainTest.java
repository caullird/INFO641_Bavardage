public class MainTest {
    public static void main(String[] args) {
        Concierge concierge = new Concierge();
        concierge.newBavard("bv1");
        concierge.newBavard("bv2");
        ConciergeWindow cw = new ConciergeWindow(concierge);
    }
}
