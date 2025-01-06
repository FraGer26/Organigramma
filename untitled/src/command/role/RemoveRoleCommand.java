package command.role;

import command.Command;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;
import visitor.management.RemoverExtendRoleVisitor;

import javax.swing.table.DefaultTableModel;

public class RemoveRoleCommand implements Command {

    private UnitaOrganizzativa unitaOrganizzativa;
    private DefaultTableModel model;
    private OrganigrammaPanel organigrammaPanel;
    public RemoveRoleCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel, DefaultTableModel model) {
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.model = model;
        this.organigrammaPanel = organigrammaPanel;

    }

    @Override
    public void execute() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            boolean isSelected = (boolean) model.getValueAt(i, 1);
            if (isSelected) {
                Role roleToRemove = (Role) model.getValueAt(i, 0);
                unitaOrganizzativa.getRoles().remove(roleToRemove);
                if(roleToRemove.extend()) {
                    RemoverExtendRoleVisitor visitor = new RemoverExtendRoleVisitor(roleToRemove);
                    visitor.visit(unitaOrganizzativa);
                }
                model.removeRow(i);
                organigrammaPanel.setModified(true);
            }
        }
    }
}
