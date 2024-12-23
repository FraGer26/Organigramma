package command.unit;

import command.Command;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

public class RemoveUnitCommand implements Command {
    private  OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public RemoveUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        if (unitaOrganizzativa.getParent() != null) {
            unitaOrganizzativa.getParent().rimuoviFiglio(unitaOrganizzativa);
            organigrammaPanel.setModified(true);
            System.out.println( unitaOrganizzativa.getParent().getFigli());
            unitaOrganizzativa.getGraphicUnit().setVisible(false);
            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();
        }
    }
}
