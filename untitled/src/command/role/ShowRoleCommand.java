package command.role;

import command.Command;
import composite.UnitaOrganizzativa;
import composite.Role;
import view.OrganigrammaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowRoleCommand implements Command {

    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public ShowRoleCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        // Creare una lista di voci (può essere qualsiasi lista di stringhe)


        // Definire il nome della colonna
        String[] colonne = {"Ruoli"};

        // Creare un modello di tabella con una sola colonna
        DefaultTableModel model = new DefaultTableModel(colonne, 0);


        // Aggiungere gli elementi della lista alla tabella
        for (Role role : unitaOrganizzativa.getRoles()) {
            model.addRow(new Object[]{role});

        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model);

        // Creare uno scroll pane per la tabella (così può scorrere se necessario)
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare una finestra per visualizzare la tabella
        JFrame tabellaFrame = new JFrame("Tabella Ruoli");
        tabellaFrame.setSize(400, 300);
        tabellaFrame.add(scrollPane, BorderLayout.CENTER);
        tabellaFrame.setVisible(true);
    }
}
