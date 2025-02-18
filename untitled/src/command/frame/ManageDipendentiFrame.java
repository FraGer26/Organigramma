package command.frame;

import command.CommandJButton;
import command.dipendenti.AddDipendentiCommand;
import command.dipendenti.RemoveDipendentiCommand;
import command.role.AddRoleCommand;
import command.role.ManageRoleCommand;
import command.role.RemoveRoleCommand;
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
    private DefaultTableModel model;
    private static final int WIDTH = 600, HEIGHT = 400;
    public ManageDipendentiFrame(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        super("Gestione Dipendenti");
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
        setUp();
    }

    private void setUp() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        // Definire le colonne della tabella
        String[] colonne = {"Nome", "Cognome", "Ruolo", "Seleziona"};

        // Crea il modello di tabella
        model = new DefaultTableModel(colonne, 0);

        // Aggiungere i dipendenti alla tabella
        for (Dipendente dipendente : unitaOrganizzativa.getDipendenti()) {

                Object[] row = {dipendente.getNome(), dipendente.getSurname(), dipendente.getRole(), Boolean.FALSE};
                model.addRow(row);

        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model){
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Permetti modifiche solo nella colonna "Seleziona"
            }
        };
        tabella.getColumn("Seleziona").setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare i pulsanti per le azioni di aggiunta e rimozione
        JPanel buttonPanel = new JPanel();

        // Aggiungere il pulsante "Aggiungi dipendenti" con il comando personalizzato
        buttonPanel.add(new CommandJButton("Aggiungi dipendenti", new AddDipendentiCommand(unitaOrganizzativa, organigrammaPanel) {
            @Override
            public void execute() {
                super.execute();
                refreshTable();
            }
        }));
        // Aggiungere il pulsante per rimuovere utenti, aggiungere ruolo, e visualizzare ruoli
        buttonPanel.add(new CommandJButton("Rimuovi Dipendente",new RemoveDipendentiCommand(unitaOrganizzativa,organigrammaPanel,model)));
        buttonPanel.add(new CommandJButton("Aggiungi ruolo", new AddRoleCommand(unitaOrganizzativa, organigrammaPanel)));
        buttonPanel.add(new CommandJButton("Visualizza ruoli", new ManageRoleCommand(unitaOrganizzativa, organigrammaPanel)));

        // Aggiungere la tabella e il pannello dei pulsanti al frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Rendi visibile la finestra
        setVisible(true);
    }
    private void refreshTable() {
        model.setRowCount(0); // Svuotare il modello della tabella
        for (Dipendente dipendente : unitaOrganizzativa.getDipendenti()) {
            Object[] row = {dipendente.getNome(), dipendente.getSurname(), dipendente.getRole(), Boolean.FALSE};
            model.addRow(row);

        }
    }
}
