import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLEditorKit;

public class InterfacePrivateMessage extends JFrame implements ActionListener {
    private PapotageListener requestor;
    private PapotageListener receiver;
    private InterfacePrivateMessage interfaceMPReceiver;
    private Concierge concierge;

    private String message = "";

    private JLabel zoneMessage = new JLabel("Chat privé:");

    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton disconnectButton = new JButton("Deconnexion");


    JTextPane zoneMessageTxt = new JTextPane();
    HTMLEditorKit kit = new HTMLEditorKit();

    private JLabel subjectLabel = new JLabel("Sujet du message :");
    private JTextField subjectField = new JTextField("",20);
    private JLabel messageLabel = new JLabel("Message :");
    private JTextField messageField = new JTextField("",30);
    private JButton sendMessageButton = new JButton("Répondre");


    public InterfacePrivateMessage (PapotageListener requestor, PapotageListener receiver){
        super();
        this.requestor = requestor;
        this.receiver = receiver;

        // Definition du titre et de la position de la fenetre
        this.setTitle("Messagerie de " + this.requestor.getName() + " à " + this.receiver.getName());
        this.setLocation(1050,200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(this.panel);


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


        panel.setBorder(border_box);
        panel.setLayout(layout);
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
        panel.add(disconnectButton);

        // Mise en place des deux boutons
        sendMessageButton.addActionListener(this);
        sendMessageButton.setActionCommand("response");

        disconnectButton.addActionListener(this);
        disconnectButton.setActionCommand("disconnect");


        pack();
        this.setVisible(false);
    }

    public void setBavardRequestor(PapotageListener unPapotageListener) {
        this.requestor = unPapotageListener;
    }
    public void setBavardReceiver(PapotageListener unPapotageListener) {
        this.receiver = unPapotageListener;
    }
    public void setConcierge(Concierge unConcierge) {
        this.concierge = unConcierge;
    }
    public void setinterfaceMPReceiver(InterfacePrivateMessage unInterfaceMp) {
        this.interfaceMPReceiver = unInterfaceMp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("disconnect") || e.getActionCommand().equals(EXIT_ON_CLOSE)) {
            this.dispose();
        }

        if(e.getActionCommand().equals("response")) {
            this.requestor.responsePrivateMessage(subjectField.getText(),messageField.getText(),this.requestor,this.receiver,this,this.interfaceMPReceiver);
            subjectField.setText("");
            messageField.setText("");
        }
    }


    public void displayMessage(PapotageEvent message, PapotageListener requestor) {
        String charString = "";
        if(this.requestor.getName() == requestor.getName()){
            charString += this.message = this.message +
                    "<i> <b>Vous avez envoyé : </b> " + message.getSubject() +   " <br/> " +
                    message.getBody() + "<br/></i>";
        }else{
            charString += this.message = this.message +
                    "<b>" + requestor.getName() + "</b> : " + message.getSubject() +   " <br/> " +
                    message.getBody() + "<br/>";
        }
        zoneMessageTxt.setText(charString);
    }
}
