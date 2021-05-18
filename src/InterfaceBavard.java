import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;

public class InterfaceBavard extends JFrame implements ActionListener {
    private Bavard bavard;
    private Concierge concierge;

    private String message = "";

    private JLabel zoneMessage = new JLabel("Chat de la conciergerie:");
    private JLabel labelBavard = new JLabel("Bavard en ligne :");

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JTextPane zoneMessageTxt = new JTextPane();
    HTMLEditorKit kit = new HTMLEditorKit();
    JEditorPane connectedList = new JEditorPane();


    private JLabel subjectLabel = new JLabel("Sujet du message :");
    private JTextField subjectField = new JTextField("",20);
    private JLabel messageLabel = new JLabel("Message :");
    private JTextField messageField = new JTextField("",30);
    private JButton sendMessageButton = new JButton("Envoyé à tous");

    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton disconnectButton = new JButton("Deconnexion");
    private JButton mpButton = new JButton("Messagerie privé");


    public InterfaceBavard (Bavard unBavard){
        super();
        this.bavard = unBavard;
        // Definition du titre et de la position de la fenetre
        this.setTitle("Messagerie de " + this.bavard.getName());
        this.setLocation(600,170);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                disconnect();
            }
        });

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(this.panel);

        // HTML
        JScrollPane onlineUserScrollPane = new JScrollPane(connectedList);
        connectedList.setEditable(false);
        connectedList.setEditorKit(kit);
        connectedList.setPreferredSize(new Dimension(250, 190));
        String charString = "<center><font color=#666666>Il n'y a pas encore d'utilisateur connecté</font></center>";
        connectedList.setText(charString);

        Border border_box = BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);

        JScrollPane messageScroolPane = new JScrollPane(this.zoneMessageTxt);
        zoneMessageTxt.setEditable(false);
        zoneMessageTxt.setEditorKit(kit);
        zoneMessageTxt.setPreferredSize(new Dimension(250, 185));


        disconnectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        zoneMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelBavard.setAlignmentX(Component.CENTER_ALIGNMENT);


        panel.setBorder(border_box);
        panel.setLayout(layout);
        panel.add(labelBavard);
        panel.add(onlineUserScrollPane);
        panel.add(zoneMessage);
        panel.add(messageScroolPane);



        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subjectField.setPreferredSize(new Dimension(250, 30));
        messageField.setPreferredSize(new Dimension(250, 30));

        panel.add(subjectLabel);
        panel.add(subjectField);
        panel.add(messageLabel);
        panel.add(messageField);



        panel.add(sendMessageButton);

        buttonPanel.add(disconnectButton);
        buttonPanel.add(mpButton);

        panel.add(buttonPanel);

        // Mise en place des deux boutons
        sendMessageButton.addActionListener(this);
        sendMessageButton.setActionCommand("newMessage");

        disconnectButton.addActionListener(this);
        disconnectButton.setActionCommand("disconnect");

        mpButton.addActionListener(this);
        mpButton.setActionCommand("mpMessage");


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

        if(e.getActionCommand().equals(EXIT_ON_CLOSE)) {
            this.disconnect();
        }

        if(e.getActionCommand().equals("newMessage")) {
            this.bavard.sendMessageAll(subjectField.getText(),messageField.getText(),this.bavard);
            subjectField.setText("");
            messageField.setText("");
        }

        if(e.getActionCommand().equals("mpMessage")) {
            InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
            iM.setConcierge(concierge);
            //this.dispose();
        }


    }

    public void disconnect(){
        this.concierge.bavardSignOut(bavard);
        this.dispose();
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


    public void displayMessage(PapotageEvent message, PapotageListener requestor) {
        String charString = "";
        if((this.bavard.getName() == requestor.getName())){
            charString += this.message = this.message +
                    "<i> <b>Vous avez envoyé : </b> " + message.getSubject() +   " <br/> " +
                    message.getBody() + "</i><br/>";
        }else{
            charString += this.message = this.message +
                    "<b>" + requestor.getName() + "</b> : " + message.getSubject() +   " <br/> " +
                    message.getBody() + "<br/>";
        }
        zoneMessageTxt.setText(charString);
    }
}
