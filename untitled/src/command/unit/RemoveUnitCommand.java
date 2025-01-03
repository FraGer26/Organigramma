package command.unit;

import command.Command;
import composite.UnitaOrganizzativa;
import exceptions.RootNotRemovableException;
import view.OrganigrammaPanel;
import visitor.RemoveChildrenVisitor;

import javax.swing.*;

public class RemoveUnitCommand implements Command {
    private  OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public RemoveUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {

        if(unitaOrganizzativa.isRoot) throw new RootNotRemovableException("la radice non puo essere rimossa");


        if(!unitaOrganizzativa.isLeaf()) {
            if(showDialog()== JOptionPane.OK_OPTION)
                    unitaOrganizzativa.accept(new RemoveChildrenVisitor(organigrammaPanel));
            else return;
        }
        unitaOrganizzativa.getParent().rimuoviFiglio(unitaOrganizzativa);
        organigrammaPanel.remove(unitaOrganizzativa.getGraphicUnit());
        organigrammaPanel.setModified(true);

        organigrammaPanel.repaint();
        organigrammaPanel.revalidate();

    }
    private int showDialog() {
        Object[] options = {"OK", "ANNULLA"};
        return JOptionPane.showOptionDialog(null,
                "Attenzione, il nodo selezionato ha dei figli, continuare con la rimozione?", "Rimozione nodo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
    }
}
