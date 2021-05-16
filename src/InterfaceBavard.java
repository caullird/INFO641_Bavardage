import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

public class InterfaceBavard<b, Jlist> extends JFrame implements ActionListener {
    private Bavard bavard;
    private Concierge concierge;

    private String message = "";

    private JLabel zoneMessage = new JLabel("Chat de la conciergerie:");
    private JLabel labelBavard = new JLabel("Bavard en ligne :");

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton disconnectButton = new JButton("Deconnexion");


    private JPanel consolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel consoleName = new JLabel("Console : ");
    private JTextField consoleInput = new JTextField("",25);
    private JButton consoleButton = new JButton("Executer");

    JTextPane zoneMessageTxt = new JTextPane();
    HTMLEditorKit kit = new HTMLEditorKit();
    JEditorPane connectedList = new JEditorPane();


    private JLabel subjectLabel = new JLabel("Sujet du message :");
    private JTextField subjectField = new JTextField("",20);
    private JLabel messageLabel = new JLabel("Message :");
    private JTextField messageField = new JTextField("",30);
    private JButton sendMessageButton = new JButton("Envoyé à tous");


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

        Border border_box = BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(panel,BoxLayout.Y_AXIS);

        JScrollPane messageScroolPane = new JScrollPane(this.zoneMessageTxt);
        zoneMessageTxt.setEditable(false);
        zoneMessageTxt.setEditorKit(kit);
        zoneMessageTxt.setPreferredSize(new Dimension(250, 185));


        disconnectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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


        consolePanel.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));
        consolePanel.add(consoleName);
        consolePanel.add(consoleInput);
        consolePanel.add(consoleButton);
        panel.add(consolePanel);


        panel.add(disconnectButton);

        // Mise en place des deux boutons
        sendMessageButton.addActionListener(this);
        sendMessageButton.setActionCommand("newMessage");

        disconnectButton.addActionListener(this);
        disconnectButton.setActionCommand("disconnect");

        consoleButton.addActionListener(this);
        consoleButton.setActionCommand("execute");


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
            //InterfaceMessage iM = new InterfaceMessage(bavard,concierge);
            //iM.setConcierge(concierge);
            this.bavard.sendMessageAll(subjectField.getText(),messageField.getText(),this.bavard);
            //this.dispose();
        }

        if(e.getActionCommand().equals("execute")){
            String command = consoleInput.getText();

            if(command.contains("!ban")){
                this.bavard.banUser(command);
            }
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

    public void displayMessage(PapotageEvent message, PapotageListener requestor, PapotageListener receiver) {
        String charString = "";
        if(this.bavard.getName() == requestor.getName()){
            charString += this.message = this.message +
                    "<i> <b>Vous avez envoyé : </b> " + message.getSubject() +   " <br/> " +
                    message.getBody() + "<br/>";
        }else{
            charString += this.message = this.message +
                    "<b>" + requestor.getName() + "</b> : " + message.getSubject() +   " <br/> " +
                    message.getBody() + "<br/>";
        }
        zoneMessageTxt.setText(charString);
    }
}
