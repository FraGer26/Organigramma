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
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            boolean isSelected = (boolean) model.getValueAt(i, 3);
            if (isSelected) {
                unitaOrganizzativa.removeDipendente(unitaOrganizzativa.getDipendenteByNameAndSurnameAndRole
                        ((String) model.getValueAt(i, 0),(String) model.getValueAt(i, 1)));
                model.removeRow(i);
                organigrammaPanel.setModified(true);
            }
        }
    }
}