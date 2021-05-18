import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class InterfaceMessage extends JFrame implements ActionListener{

    private Bavard bavard;
    private Concierge concierge;

    private JComboBox contactList = new JComboBox();
    private JLabel receiverLabel = new JLabel("Envoyé à :");
    private JLabel subjectLabel = new JLabel("Sujet du message :");
    private JTextField subjectField = new JTextField("",20);
    private JLabel messageLabel = new JLabel("Message :");
    private JTextField messageField = new JTextField("",30);

    private JPanel inputPanel = new JPanel();
    private JPanel inputButton = new JPanel();

    private JButton sendOneButton = new JButton("Envoyer");
    private JButton annulationButton = new JButton("Annuler");


    // Constructeur
    public InterfaceMessage(Bavard bavard, Concierge concierge) {
        super();

        this.bavard = bavard;
        this.concierge = concierge;

        setTitle("Messagerie privé");
        this.setSize(800,500);
        this.setLocationRelativeTo(null);

        for(PapotageListener unPapotageListener : this.concierge.getPapotageListeners()) {
            if((unPapotageListener.getName() != this.bavard.getName()) && unPapotageListener.getConnected()) {
                contactList.addItem(unPapotageListener.getName());
            }
        }

        sendOneButton.addActionListener(this);
        sendOneButton.setActionCommand("sendOne");

        annulationButton.addActionListener(this);
        annulationButton.setActionCommand("cancel");

        // Creation du container
        Container mainContainer = this.getContentPane();
        mainContainer.add(this.inputPanel);

        // Creation des bordures
        Border border =  BorderFactory.createEmptyBorder(30,35,30,35);

        // Creation du Layout
        BoxLayout layout = new BoxLayout(inputPanel,BoxLayout.Y_AXIS);

        // Centrage des elements
        receiverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendOneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        annulationButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Mise en place de inputPanel
        inputPanel.setBorder(border);
        inputPanel.setLayout(layout);
        inputPanel.add(receiverLabel);
        inputPanel.add(contactList);
        inputPanel.add(subjectLabel);
        inputPanel.add(subjectField);
        inputPanel.add(messageLabel);
        inputPanel.add(messageField);
        inputButton.add(sendOneButton);
        inputButton.add(annulationButton);
        inputPanel.add(inputButton);

        pack();
        setVisible(true);
    }

    // Utilisation des boutons
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("cancel")) {
            this.dispose();
        }

        if(e.getActionCommand().equals("sendOne")) {
            Object selected = this.contactList.getSelectedItem();
            PapotageListener destinataire = this.findOneObject(selected);
            this.bavard.createMessagePrivate(subjectField.getText(), messageField.getText(), destinataire,this.bavard);
            this.dispose();
        }
    }

    public PapotageListener findOneObject(Object selected) {
        for(PapotageListener unPapotageListener : this.concierge.getPapotageListeners()) {
            if (selected.equals(unPapotageListener.getName())){
                return unPapotageListener;
            }
        }
        return null;
    }

    public Bavard getBavard() {
        return bavard;
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

}
