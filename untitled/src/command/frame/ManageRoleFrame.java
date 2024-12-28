package command.frame;

import command.CommandJButton;
import command.dipendenti.AddDipendentiCommand;
import command.role.AddRoleCommand;
import command.role.RemoveRoleCommand;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageRoleFrame extends JFrame {
    private UnitaOrganizzativa unitaOrganizzativa;
    private OrganigrammaPanel organigrammaPanel;
    private DefaultTableModel model;

    public ManageRoleFrame(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        super("Gestione Ruoli");
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
        setUp();
    }

    private void setUp() {
        // Definire il nome delle colonne
        String[] colonne = {"Ruoli", "Seleziona"};

        // Creare un modello di tabella con due colonne: una per la checkbox e una per il nome del ruolo
        model = new DefaultTableModel(colonne, 0);

        // Aggiungere gli elementi della lista alla tabella
        for (Role role : unitaOrganizzativa.getRoles()) {
            model.addRow(new Object[]{role, Boolean.FALSE});
        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Permetti modifiche solo nella colonna "Seleziona"
            }
        };
        tabella.getColumn("Seleziona").setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Configurare la finestra
        setSize(500, 400);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        // Bottone per aggiungere un nuovo ruolo

        buttonPanel.add(new CommandJButton("Aggiungi Ruoli", new AddRoleCommand(unitaOrganizzativa, organigrammaPanel) {
            @Override
            public void execute() {
                super.execute();
                refreshTable();
            }
        }));
        buttonPanel.add(new CommandJButton("Rimuovi Ruoli Selezionati",new RemoveRoleCommand(unitaOrganizzativa,organigrammaPanel,model)));


        // Aggiungere il pannello dei bottoni alla finestra
        add(buttonPanel, BorderLayout.SOUTH);

        // Impostare la finestra come visibile
        setVisible(true);
    }

    private void refreshTable() {
        model.setRowCount(0); // Svuotare il modello della tabella
        for (Role role : unitaOrganizzativa.getRoles()) {
            model.addRow(new Object[]{role, Boolean.FALSE});
        }
    }
}