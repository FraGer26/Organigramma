package command.dipendenti;

import command.Command;
import composite.Dipendente;
import composite.UnitaOrganizzativa;
import composite.Role;
import view.OrganigrammaPanel;

import javax.swing.*;

public class AddDipendentiCommand  implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public AddDipendentiCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        // Creare un pannello personalizzato con tre campi di input
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField nomeField = new JTextField(10);
        JTextField cognomeField = new JTextField(10);  // Cambiato il nome del campo da stipendio a cognome


        // Creare il JComboBox per il ruolo
        JComboBox<String> ruoloComboBox = new JComboBox<>();

        // Ottieni i ruoli da unita.getRoles() e aggiungili al JComboBox
        for (Role ruolo : unitaOrganizzativa.getRoles()) {
            ruoloComboBox.addItem(ruolo.role());  // Aggiungi il nome del ruolo
        }

        // Aggiungere i componenti al pannello
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Cognome:"));
        panel.add(cognomeField);
        panel.add(new JLabel("Ruolo:"));
        panel.add(ruoloComboBox);

        // Mostrare il dialogo
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Aggiungi Dipendente",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Gestire l'input
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cognome = cognomeField.getText();
            String ruoloSelezionato = (String) ruoloComboBox.getSelectedItem();

            // Validazione semplice
            if (nome.isEmpty() || cognome.isEmpty()  || ruoloSelezionato == null) {
                JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
            } else {
                // Trova il ruolo corrispondente al nome selezionato
                Role ruolo = null;
                for (Role r : unitaOrganizzativa.getRoles()) {
                    if (r.role().equals(ruoloSelezionato)) {
                        ruolo = r;
                        break;
                    }
                }
                // Aggiungere il nuovo dipendente
                Dipendente nuovoDipendente = new Dipendente(unitaOrganizzativa, nome, cognome, ruolo);
                unitaOrganizzativa.aggiungiFiglio(nuovoDipendente); // Aggiungere il nuovo dipendente

            }
        }
    }
}
