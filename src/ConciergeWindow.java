import javax.swing.*;
import java.awt.*;

public class ConciergeWindow extends JFrame {
    private static final Dimension DIMENSION = new Dimension(300, 500);

    private JPanel messagesPan = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JList jList1 = new JList();
    private JPanel jPanel1 = new JPanel();
    private JTextField jTextField1 = new JTextField();
    private JButton jButton1 = new JButton();

    public ConciergeWindow(Concierge concierge) {

        BoxLayout messagesLayout = new BoxLayout(this.messagesPan, BoxLayout.Y_AXIS);
        this.messagesPan.setLayout(messagesLayout);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("Bavard - ");
        this.add(this.createTextBox(), BorderLayout.SOUTH);
        this.add(messagesPan);
        this.setVisible(true);
    }

    private Component createTextBox() {
        //JPanel pan = new JPanel(new BorderLayout());
        //JButton send = new JButton("Envoyer");
        //pan.add(send, BorderLayout.EAST);
        //pan.add(this.textArea);
        return null;
    }
}
