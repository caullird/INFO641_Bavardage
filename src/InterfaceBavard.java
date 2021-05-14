import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;

public class InterfaceBavard extends JFrame implements ActionListener {
    private Bavard bavard;
    private Concierge concierge;

    private JLabel receivedMessage = new JLabel("Messages reçus :");
    private JLabel sendMessage = new JLabel("Messages envoyés :");
    private JLabel labelBavard = new JLabel("Bavard en ligne :");

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton sendMessageButton = new JButton("Nouveau Message");
    private JButton disconnectButton = new JButton("Deconnexion");

    JTextPane zoneReceived = new JTextPane();
    private String messageReceivedString = "";
    JTextPane zoneSend = new JTextPane();
    private String messageSendString = "";

    HTMLEditorKit kit = new HTMLEditorKit();

    JEditorPane connectedList = new JEditorPane();

    // Construction
    public InterfaceBavard (Bavard unBavard){
        super();
        this.bavard = unBavard;

        // Definition du titre et de la position de la fenetre
        this.setTitle("Messagerie de " + this.bavard.getName());
        this.setLocation(1050,200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(this.panel);

        // HTML
        JScrollPane onlineUserScrollPane = new JScrollPane(connectedList);
        connectedList.setEditable(false);
        connectedList.setEditorKit(kit);
        connectedList.setPreferredSize(new Dimension(250, 185));
        String charString = "<center><font color=#666666>Il n'y a pas encore d'utilisateur connecté</font></center>";
        connectedList.setText(charString);

        Border border_box =  BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);

        JScrollPane receivedScrollPane = new JScrollPane(this.zoneReceived);
        zoneReceived.setEditable(false);
        zoneReceived.setEditorKit(kit);
        zoneReceived.setPreferredSize(new Dimension(250, 185));

        JScrollPane sendScroolPane = new JScrollPane(this.zoneSend);
        zoneSend.setEditable(false);
        zoneSend.setEditorKit(kit);
        zoneSend.setPreferredSize(new Dimension(250, 185));

        disconnectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.setBorder(border_box);
        panel.setLayout(layout);
        panel.add(receivedMessage);
        panel.add(receivedScrollPane);
        panel.add(sendMessage);
        panel.add(sendScroolPane);
        panel.add(labelBavard);
        panel.add(onlineUserScrollPane);
        panel.add(sendMessageButton);
        panel.add(disconnectButton);

        // Mise en place des deux boutons
        sendMessageButton.addActionListener(this);
        sendMessageButton.setActionCommand("newMessage");

        disconnectButton.addActionListener(this);
        disconnectButton.setActionCommand("disconnect");

        pack();
        this.setVisible(false);
    }

    public void setBavard(Bavard b) {
        this.bavard = b;
    }

    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("disconnect") || e.getActionCommand().equals(EXIT_ON_CLOSE)) {
            this.concierge.bavardSignOut(bavard);
            this.dispose();
        }

        if(e.getActionCommand().equals("newMessage")) { // Creation d'une interfaceMessage
            InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
            iM.setConcierge(concierge);
        }
    }

    public void displayOnlineUser() {
        String charString = "";
        for (PapotageListener bavard : concierge.getPapotageListeners()) {
            if(!bavard.equals(this.bavard)){
                if (bavard.getConnected()) {
                    charString +=  bavard.getName() + " : " + "<font color=green>est maintenant en ligne ! </font> <br/>";
                }else {
                    charString +=  bavard.getName() + " : " + "<font color=red>est hors-ligne</font> <br/>";
                }
            }else{
                charString +=  bavard.getName() + " : <font color=green>Vous êtes maintenant connecté </font> <br/>";
            }
        }
        connectedList.setText(charString);
    }

    public void displayAlertMessage(PapotageEvent unAlert){

    }

    public void displayMessageReceived(PapotageEvent message, PapotageListener requestor) {
        String charString ="";
        charString += this.messageReceivedString = this.messageReceivedString +
                "<b>De : </b>"+ requestor.getName() +"<br/>" + "<b>Sujet : </b>" + message.getSubject() + "<br/>" + message.getBody() +"<br/>";

        zoneReceived.setText(charString);
    }

    public void displayMessageSend(PapotageEvent message, PapotageListener receiver) {
        String charString = "";
        charString += this.messageSendString = this.messageSendString
                + "<b>À : </b>" + receiver.getName() + "<br/>"+"<b>Sujet : </b>" + message.getSubject() + "<br/>" + message.getBody() +"<br/>";
        zoneSend.setText(charString);
    }




}
