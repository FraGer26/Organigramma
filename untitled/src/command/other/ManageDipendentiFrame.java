package command.other;

import command.CommandJButton;
import command.dipendenti.AddDipendentiCommand;
import command.role.AddRoleCommand;
import command.role.ManageRoleCommand;
import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageDipendentiFrame extends JFrame {
    private UnitaOrganizzativa unitaOrganizzativa;
    private OrganigrammaPanel organigrammaPanel;

    public ManageDipendentiFrame(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        super("Gestione Dipendenti");
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
        setUp();
    }

    private void setUp() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);

        // Definire le colonne della tabella
        String[] colonne = {"Nome", "Cognome", "Ruolo", "Seleziona"};

        // Crea il modello di tabella
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Aggiungere i dipendenti alla tabella
        for (ComponenteOrganizzativo componenteOrganizzativo : unitaOrganizzativa.getFigli()) {
            if (componenteOrganizzativo.isDipendente()) {
                Dipendente dipendente = (Dipendente) componenteOrganizzativo;
                Object[] row = {dipendente.getNome(), dipendente.getSurname(), dipendente.getRole(), Boolean.FALSE};
                model.addRow(row);
            }
        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model);
        tabella.getColumn("Seleziona").setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare i pulsanti per le azioni di aggiunta e rimozione
        JPanel buttonPanel = new JPanel();
        // JButton aggiungiButton = new JButton("Aggiungi Utente");
        JButton rimuoviButton = new JButton("Rimuovi Utente");

        // Aggiungere l'azione al pulsante "Rimuovi Utente"
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Rimuovere gli utenti selezionati
                for (int i = 0; i < tabella.getRowCount(); i++) {
                    boolean isSelected = (boolean) tabella.getValueAt(i, 3); // La colonna 3 è la checkbox
                    if (isSelected) {
                        // Rimuovere il dipendente selezionato
                        Dipendente dipendente = (Dipendente) unitaOrganizzativa.getFigli().get(i);
                        unitaOrganizzativa.getFigli().remove(dipendente);
                        model.removeRow(i);
                        organigrammaPanel.setModified(true);
                        i--; // Dopo la rimozione, il prossimo elemento è spostato indietro
                        repaint();
                    }
                }
            }
        });

        // Aggiungere il pulsante "Aggiungi dipendenti" con il comando personalizzato
        buttonPanel.add(new CommandJButton("Aggiungi dipendenti", new AddDipendentiCommand(unitaOrganizzativa, organigrammaPanel) {
            @Override
            public void execute() {
                super.execute();
                setUp();

            }
        }));

        // Aggiungere il pulsante per rimuovere utenti, aggiungere ruolo, e visualizzare ruoli
        buttonPanel.add(rimuoviButton);
        buttonPanel.add(new CommandJButton("Aggiungi ruolo", new AddRoleCommand(unitaOrganizzativa, organigrammaPanel)));
        buttonPanel.add(new CommandJButton("Visualizza ruoli", new ManageRoleCommand(unitaOrganizzativa, organigrammaPanel)));

        // Aggiungere la tabella e il pannello dei pulsanti al frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Rendi visibile la finestra
        setVisible(true);
    }
}
