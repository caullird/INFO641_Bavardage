import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceRegister extends JFrame implements ActionListener {

    private Concierge concierge;

    private JButton loginButton = new JButton("Connexion");

    private JLabel labelName = new JLabel("Nom du bavard");
    private JTextField inputName = new JTextField("",25);

    private JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private InterfaceGestionnaire iG;

    public InterfaceRegister(){
        super();

        setTitle("Connexion");
        setLocation(250, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout des boutons comme ecouteurs
        loginButton.addActionListener(this);
        loginButton.setActionCommand("signIn");

        // Ajout des elements dans le panel
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));
        loginPanel.add(labelName);
        loginPanel.add(inputName);
        loginPanel.add(loginButton);
        setContentPane(loginPanel);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "signIn") {
            Bavard bavard = this.concierge.bavardSignIn(inputName.getText());
            if(bavard != null){
                bavard.getiB().setVisible(true);
            }
        }
    }

    public void setiG(InterfaceGestionnaire iG) {
        this.iG = iG;
    }

    public Concierge setConcierge(Concierge c) {
        return concierge = c;
    }


}
