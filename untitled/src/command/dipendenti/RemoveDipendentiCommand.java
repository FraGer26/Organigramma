package command.dipendenti;

import command.Command;
import command.frame.ManageRoleFrame;
import composite.Dipendente;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.table.DefaultTableModel;

public class RemoveDipendentiCommand implements Command {

    private UnitaOrganizzativa unitaOrganizzativa;
    private DefaultTableModel model;
    private OrganigrammaPanel organigrammaPanel;
    public RemoveDipendentiCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel, DefaultTableModel model) {
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.model = model;
        this.organigrammaPanel = organigrammaPanel;

    }

    @Override
    public void execute() {
        // Rimuovere gli utenti selezionati
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean isSelected = (boolean) model.getValueAt(i, 3); // La colonna 3 è la checkbox
            if (isSelected) {
                // Rimuovere il dipendente selezionato
                Dipendente dipendente = unitaOrganizzativa.getDipendenteByName(model.getValueAt(i, 0).toString());
                if(dipendente == null) { return;}
                unitaOrganizzativa.removeDipendente(dipendente);
                model.removeRow(i);
                organigrammaPanel.setModified(true);
                i--; // Dopo la rimozione, il prossimo elemento è spostato indietro

            }
        }
    }
}