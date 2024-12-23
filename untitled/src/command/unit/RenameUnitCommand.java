package command.unit;

import command.Command;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class RenameUnitCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public RenameUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }
    @Override
    public void execute() {
        String nameRole = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if (nameRole != null && !nameRole.isEmpty()) {
            unitaOrganizzativa.setNome(nameRole);
            organigrammaPanel.setModified(true);
            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();

        }

    }
}
