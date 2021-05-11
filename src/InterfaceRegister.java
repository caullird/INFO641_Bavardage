import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceRegister extends JFrame implements ActionListener {

    private Concierge concierge;

    private JButton boutonConn = new JButton("Connexion");

    private JLabel label = new JLabel("Nom du bavard");
    private JTextField corps = new JTextField("",10);

    private JPanel panel = new JPanel();

    public InterfaceRegister(Concierge concierge){
        super();

        this.concierge = concierge;

        setTitle("Connexion");
        setLocation(250, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial",Font.BOLD,14);
        label.setFont(font);

        // Ajout des boutons comme ecouteurs
        boutonConn.addActionListener(this);
        boutonConn.setActionCommand("signIn");

        // Creation des bordures
        Border border1=  BorderFactory.createEmptyBorder(20,25,20,25);

        // Ajout des elements dans le panel
        panel.setBorder(border1);
        panel.add(label);
        panel.add(corps);
        panel.add(boutonConn);
        setContentPane(panel);

        // Alignement des elements
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.corps.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.boutonConn.setAlignmentX(Component.CENTER_ALIGNMENT);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "signIn") {
            this.concierge.bavardSignIn(corps.getText());
            corps.setText("");
        }
    }
}
