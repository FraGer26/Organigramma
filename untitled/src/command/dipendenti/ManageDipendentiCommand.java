package command.dipendenti;

import command.Command;
import command.frame.ManageDipendentiFrame;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

public class ManageDipendentiCommand implements Command {
    private  final OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public ManageDipendentiCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }

    @Override
    public void execute() {
        new ManageDipendentiFrame(unitaOrganizzativa, organigrammaPanel);
    }
}


