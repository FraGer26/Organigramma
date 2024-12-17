package command.dipendenti;

import command.Command;
import command.other.GestioneDipendentiFrame;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

public class ShowDipendentiCommand  implements Command {
    private  final OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public ShowDipendentiCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }

    @Override
    public void execute() {
        new GestioneDipendentiFrame(unitaOrganizzativa, organigrammaPanel);
    }
}


