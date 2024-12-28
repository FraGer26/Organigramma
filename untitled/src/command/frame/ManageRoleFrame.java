package command.frame;

import command.CommandJButton;
import command.role.AddRoleCommand;
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
    public ManageRoleFrame(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        super("Gestione Ruoli");
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
        setUp();
    }
    private void setUp() {
        // Definire il nome delle colonne
        String[] colonne = { "Ruoli","Seleziona"};

        // Creare un modello di tabella con due colonne: una per la checkbox e una per il nome del ruolo
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Aggiungere gli elementi della lista alla tabella
        for (Role role : unitaOrganizzativa.getRoles()) {
            model.addRow(new Object[]{ role,Boolean.FALSE});
        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model) {
            // Rendere la colonna della checkbox non modificabile
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        tabella.getColumn("Seleziona").setCellEditor(new DefaultCellEditor(new JCheckBox()));

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare una finestra per visualizzare la tabella

        setSize(500, 400);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Bottone per aggiungere un nuovo ruolo


        // Bottone per rimuovere i ruoli selezionati
        JButton removeButton = new JButton("Rimuovi Ruoli Selezionati");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ciclo su tutte le righe per rimuovere i ruoli selezionati
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isSelected = (boolean) tabella.getValueAt(i, 1);
                    if (isSelected) {
                        // Rimuovere il ruolo selezionato
                        Role roleToRemove = (Role) model.getValueAt(i, 0);
                        unitaOrganizzativa.getRoles().remove(roleToRemove);
                        model.removeRow(i);
                        i--;
                        repaint();
                    }
                }
                // Ricaricare la tabella per riflettere i cambiamenti

                //tabellaFrame.dispose();  // Chiudere la finestra precedente
            }
        });

        // Creare un pannello per i bottoni e aggiungerli
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new CommandJButton("Aggiungi Ruoli",new AddRoleCommand(unitaOrganizzativa,organigrammaPanel){
            @Override
            public void execute() {
                super.execute();
                setUp();

            }
        }));
        buttonPanel.add(removeButton);

        // Aggiungere il pannello dei bottoni alla finestra
        add(buttonPanel, BorderLayout.SOUTH);

        // Impostare la finestra come visibile
        setVisible(true);
    }
}