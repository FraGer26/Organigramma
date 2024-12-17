package command.role;

import command.Command;
import composite.UnitaOrganizzativa;
import composite.Role;
import view.OrganigrammaPanel;

import javax.swing.*;

public class AddRoleCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public AddRoleCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        String nameRole = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if (nameRole != null && !nameRole.isEmpty()) {
            unitaOrganizzativa.getRoles().add(new Role(nameRole,true));
            organigrammaPanel.repaint();

        }
    }
}
