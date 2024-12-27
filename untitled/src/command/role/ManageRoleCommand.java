package command.role;

import command.Command;
import command.frame.ManageRoleFrame;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

public class ManageRoleCommand implements Command {

    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public ManageRoleCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }

    @Override
    public void execute() {
       new ManageRoleFrame(unitaOrganizzativa, organigrammaPanel);
    }
}
