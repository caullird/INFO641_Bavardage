import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfaceGestionnaire extends JFrame implements ActionListener {
    private Concierge concierge;

    private JPanel listernerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel listernerChatLabel = new JLabel("Liste des discussions :");
    private JLabel listernerOnlineLabel = new JLabel("Bavards connectés :");

    private String message = "";
    private JTextField corps = new JTextField("",15);

    private JTextPane zoneMessages = new JTextPane();
    private JTextPane connectedList = new JTextPane();
    HTMLEditorKit kit = new HTMLEditorKit();

    private JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel labelNom = new JLabel("Nom du bavard :");
    private JButton boutonNewb = new JButton("Ajouter un bavard");

    public InterfaceGestionnaire(Concierge concierge){
        super();

        this.concierge = concierge;

        // Création de la fenêtre du concierge
        setTitle("Fenetre du concierge");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // HTML
        JScrollPane scrollPaneOnline = new JScrollPane(connectedList);
        connectedList.setPreferredSize(new Dimension(250, 185));
        connectedList.setEditable(false);
        connectedList.setEditorKit(kit);
        connectedList.setText("<font color=red>Il n'y a pas encore d'utilisateur connectés</font>");

        // Mise en place de formPanel
        formPanel.add(labelNom);
        formPanel.add(corps);
        formPanel.add(boutonNewb);
        formPanel.setVisible(true);

        // Creation du container
        Container mainContainer = this.getContentPane();
        System.out.println(mainContainer);
        mainContainer.add(listernerPanel);

        // Creation des bordures
        Border border1 =  BorderFactory.createEmptyBorder(30,35,30,35);
        Border border2 =  BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(listernerPanel,BoxLayout.Y_AXIS);

        // Creation de la zone Messages
        JScrollPane scrollPaneMessage = new JScrollPane(this.zoneMessages);
        zoneMessages.setEditable(false);
        zoneMessages.setEditorKit(kit);
        zoneMessages.setPreferredSize(new Dimension(250, 185));

        // Mise en place de listernerPanel
        listernerPanel.setBorder(border1);
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
        iR.setiG(this);
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
                InterfaceBavard iB = new InterfaceBavard(bavard);
                iB.setBavard(bavard);
                iB.setConcierge(concierge);
                bavard.setiB(iB);
            }

            corps.setText("");
        }
    }

    public void displayOnlineUser() {
        String charString = "";
        System.out.println(concierge.getPapotageListeners());
        for (PapotageListener bavard : concierge.getPapotageListeners()) {
            if (bavard.getConnected()) {
                charString +=  bavard.getName() + " : " + "<font color=green>est maintenant en ligne ! </font> <br/>";
            }else {
                charString +=  bavard.getName() + " : " + "<font color=red>est hors-ligne</font> <br/>";
            }
        }
        connectedList.setText(charString);
    }

    public void displayMessage(PapotageEvent mess, PapotageListener envoyeur, PapotageListener destinataire) {
        String charString = "";
        charString += this.message = this.message +
                "Message de " + envoyeur.getName()+ " à </b>" + destinataire.getName() + "<br/>"+
                "<b>Sujet : </b>" + mess.getSubject() + "<br/> " + mess.getBody() + "<br/>";
        zoneMessages.setText(charString);
    }

    public Concierge getConcierge() {
        return concierge;
    }
}
