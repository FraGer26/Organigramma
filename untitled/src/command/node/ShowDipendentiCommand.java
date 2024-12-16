package command.node;

import command.Command;
import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ShowDipendentiCommand  implements Command {
    private  final OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public ShowDipendentiCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }

    @Override
    public void execute() {
        // Creare una lista di dipendenti (da sostituire con i tuoi dati reali)


        // Definire le colonne per la tabella
        String[] colonne = {"Nome", "Cognome", "Ruolo"};

        // Creare un modello di tabella con i dati dei dipendenti
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Aggiungere i dati dei dipendenti al modello della tabella
        for (ComponenteOrganizzativo componenteOrganizzativo : unitaOrganizzativa.getFigli()) {
            if(componenteOrganizzativo.isDipendente()) {
                Dipendente dipendente=(Dipendente) componenteOrganizzativo;
                Object[] row = {dipendente.getNome(), dipendente.getSurname(), dipendente.getRole().toString()};
                model.addRow(row);
            }
        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model);

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare una finestra per visualizzare la tabella
        JFrame tabellaFrame = new JFrame("Tabella Dipendenti");
        tabellaFrame.setSize(400, 300);
        tabellaFrame.add(scrollPane, BorderLayout.CENTER);
        tabellaFrame.setVisible(true);
    }
}
