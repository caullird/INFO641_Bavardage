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

    private JComboBox contactList = new JComboBox();
    private JLabel receiverLabel = new JLabel("Envoyé à :");
    private JLabel subjectLabel = new JLabel("Sujet du message :");
    private JTextField subjectField = new JTextField("",20);
    private JLabel messageLabel = new JLabel("Message :");
    private JTextField messageField = new JTextField("",30);

    private JPanel inputPanel = new JPanel();
    private JPanel inputButton = new JPanel();

    private JButton sendAllButton = new JButton("Envoyer a tous");
    private JButton sendOneButton = new JButton("Envoyer");
    private JButton annulationButton = new JButton("Annuler");

    private Bavard bavard;
    private Concierge concierge;

    // Constructeur
    public InterfaceMessage(Bavard bavard, Concierge concierge) {
        super();

        this.bavard = bavard;
        this.concierge = concierge;

        setTitle("Nouveau Message");
        this.setSize(800,500);
        this.setLocationRelativeTo(null);

        for(PapotageListener pl : this.concierge.getPapotageListeners()) {
            contactList.addItem(pl.getName());
        }

        // Mise en place des boutons
        sendAllButton.addActionListener(this);
        sendAllButton.setActionCommand("sendAll");

        sendOneButton.addActionListener(this);
        sendOneButton.setActionCommand("sendOne");

        annulationButton.addActionListener(this);
        annulationButton.setActionCommand("annulation");

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
        sendAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        inputButton.add(sendAllButton);
        inputButton.add(sendOneButton);
        inputButton.add(annulationButton);
        inputPanel.add(inputButton);

        pack();
        setVisible(true);
    }

    // Utilisation des boutons
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("annulation")) { // Ferme la fenetre
            this.dispose();
        }

        if(e.getActionCommand().equals("sendOne")) { // Envoie un message a un seul destinataire
            Object selected = this.contactList.getSelectedItem();
            PapotageListener destinataire = this.findOneObject(selected);
            this.bavard.sendMessageOne(subjectField.getText(), messageField.getText(), destinataire,this.bavard);
            this.dispose();
        }
    }

    public Bavard getBavard() {
        return bavard;
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public PapotageListener findOneObject(Object selected) {
        for(PapotageListener pl : this.concierge.getPapotageListeners()) {
            if (selected.equals(pl.getName())){
                return pl;
            }
        }
        return null;
    }

    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }

}
