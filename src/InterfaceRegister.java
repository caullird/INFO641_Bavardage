import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceRegister extends JFrame implements ActionListener {

    private Concierge concierge;
    private InterfaceGestionnaire interfaceGestionnaire;

    private JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel labelName = new JLabel("Username:");
    private JTextField inputName = new JTextField("",15);
    private JButton loginButton = new JButton("Connexion");

    private JTextPane alertMessage = new JTextPane();

    HTMLEditorKit kit = new HTMLEditorKit();

    public InterfaceRegister(){
        super();

        setTitle("Connexion");
        setLocation(100, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(this.loginPanel);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(loginPanel,BoxLayout.Y_AXIS);

        // Mise en place de formPanel
        formPanel.add(labelName);
        formPanel.add(inputName);
        formPanel.add(loginButton);
        formPanel.setVisible(true);


        alertMessage.setEditable(false);
        alertMessage.setEditorKit(kit);


        loginPanel.setLayout(layout);
        loginPanel.add(formPanel);
        loginPanel.add(alertMessage);
        loginPanel.add(alertMessage);
        loginPanel.setPreferredSize(new Dimension(485, 80));

        loginButton.addActionListener(this);
        loginButton.setActionCommand("signIn");

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
            inputName.setText("");
        }
    }

    public void displayInformationMessage(String message, Boolean isError){
        if(isError){
            alertMessage.setText("<center><font color=red>" + message + "</font></center>");
        }else{
            alertMessage.setText("<center><font color=green>" + message + "</font></center>");
        }
    }

    public void setInterfaceGestionnaire(InterfaceGestionnaire interfaceGestionnaire) {
        this.interfaceGestionnaire = interfaceGestionnaire;
    }

    public Concierge setConcierge(Concierge c) {
        return concierge = c;
    }


}
