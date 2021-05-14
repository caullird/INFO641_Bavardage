import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceRegister extends JFrame implements ActionListener {

    private Concierge concierge;
    private InterfaceGestionnaire iG;

    private JButton loginButton = new JButton("Connexion");

    private JLabel labelName = new JLabel("Nom du bavard");
    private JTextField inputName = new JTextField("",25);

    private JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JTextPane alertMessage = new JTextPane();

    HTMLEditorKit kit = new HTMLEditorKit();

    public InterfaceRegister(){
        super();

        setTitle("Connexion");
        setLocation(250, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout des boutons comme ecouteurs
        loginButton.addActionListener(this);
        loginButton.setActionCommand("signIn");

        // Ajout des elements dans le panel
        loginPanel.add(labelName);
        loginPanel.add(inputName);
        loginPanel.add(loginButton);
        setContentPane(loginPanel);


        loginPanel.setPreferredSize(new Dimension(450, 120));


        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "signIn") {
            Bavard bavard = this.concierge.bavardSignIn(inputName.getText(),this);
            if(bavard != null){
                bavard.getiB().setVisible(true);
            }
        }
    }

    public void displayInformationMessage(String message, Boolean isError){
        alertMessage.setEditable(false);
        alertMessage.setEditorKit(kit);
        loginPanel.add(alertMessage);
        if(isError){
            alertMessage.setText("<font color=red>" + message + "</font>");
        }else{
            alertMessage.setText("<font color=green>" + message + "</font>");
        }
    }

    public void setiG(InterfaceGestionnaire iG) {
        this.iG = iG;
    }

    public Concierge setConcierge(Concierge c) {
        return concierge = c;
    }


}
