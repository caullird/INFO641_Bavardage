import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestionnaire extends JFrame implements ActionListener {
    private Concierge concierge;

    private JPanel listernerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel listernerChatLabel = new JLabel("Liste des discussions :");
    private JLabel listernerOnlineLabel = new JLabel("Console :");

    private String message = "";
    private JTextField corps = new JTextField("",15);

    private JTextPane zoneMessages = new JTextPane();
    private JTextPane connectedList = new JTextPane();
    HTMLEditorKit kit = new HTMLEditorKit();

    private JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel labelName = new JLabel("Nom du bavard :");
    private JButton boutonNewb = new JButton("Ajouter un bavard");

    public InterfaceGestionnaire(Concierge concierge){
        super();

        this.concierge = concierge;

        // Création de la fenêtre du concierge
        setTitle("Fenetre du concierge");
        setLocation(100, 170);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // HTML
        JScrollPane scrollPaneOnline = new JScrollPane(connectedList);
        connectedList.setPreferredSize(new Dimension(250, 185));
        connectedList.setEditable(false);
        connectedList.setEditorKit(kit);
        connectedList.setText("<font color=red>Il n'y a pas encore d'utilisateur connectés</font>");

        // Mise en place de formPanel
        formPanel.add(labelName);
        formPanel.add(corps);
        formPanel.add(boutonNewb);
        formPanel.setVisible(true);

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(listernerPanel);

        // Creation des bordures
        Border border =  BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(listernerPanel,BoxLayout.Y_AXIS);

        // Centrage des elements
        listernerChatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        listernerOnlineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creation de la zone Messages
        JScrollPane scrollPaneMessage = new JScrollPane(this.zoneMessages);
        zoneMessages.setEditable(false);
        zoneMessages.setEditorKit(kit);
        zoneMessages.setPreferredSize(new Dimension(250, 185));

        // Mise en place de listernerPanel
        listernerPanel.setBorder(border);
        listernerPanel.setLayout(layout);
        listernerPanel.add(listernerChatLabel);
        listernerPanel.add(scrollPaneMessage);
        listernerPanel.add(listernerOnlineLabel);
        listernerPanel.add(scrollPaneOnline);
        listernerPanel.add(formPanel);

        // Ajout des boutons comme ecouteurs
        boutonNewb.addActionListener(this);
        boutonNewb.setActionCommand("createBavard");

        InterfaceRegister iR = new InterfaceRegister();
        iR.setConcierge(concierge);
        iR.setInterfaceGestionnaire(this);
        concierge.setInterfaceGestionnaire(this);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "createBavard") {
            String nameBavard = corps.getText();

            if(!nameBavard.isEmpty()){
                Bavard bavard = concierge.newBavard(nameBavard);
                InterfaceBavard interfaceBavard = new InterfaceBavard(bavard);
                interfaceBavard.setBavard(bavard);
                interfaceBavard.setConcierge(concierge);
                bavard.setiB(interfaceBavard);
            }

            corps.setText("");
        }
    }

    public void displayOnlineUser() {
        String charString = "";
        for (PapotageListener bavard : concierge.getPapotageListeners()) {
            if (bavard.getConnected()) {
                charString +=  bavard.getName() + " : " + "<font color=green>est maintenant en ligne ! </font> <br/>";
            }else {
                charString +=  bavard.getName() + " : " + "<font color=red>est hors-ligne</font> <br/>";
            }
        }
        connectedList.setText(charString);
    }

    public void displayMessage(PapotageEvent message, PapotageListener requestor, PapotageListener receiver) {
        String charString = "";
        charString += this.message = this.message +
                "Message de " + requestor.getName() + " à </b>" + receiver.getName() + "<br/>"+
                "<b>Sujet : </b>" + message.getSubject() + "<br/> " + message.getBody() + "<br/>";
        zoneMessages.setText(charString);
    }



    public void displayAlertMessage(PapotageEvent unAlert){

    }

    public Concierge getConcierge() {
        return concierge;
    }
}
