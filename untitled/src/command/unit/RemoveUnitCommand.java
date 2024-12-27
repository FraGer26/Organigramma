package command.unit;

import command.Command;
import composite.UnitaOrganizzativa;
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

            if(unitaOrganizzativa.isRoot) throw new RuntimeException("RootNotRemovableEception");
            else {
                unitaOrganizzativa.getParent().rimuoviFiglio(unitaOrganizzativa);
                if(!unitaOrganizzativa.isLeaf()) {
                    Object[] options = {"OK", "ANNULLA"};
                    int risposta = JOptionPane.showOptionDialog(null,
                            "Attenzione, il nodo selezionato ha dei figli, continuare con la rimozione?", "Rimozione nodo",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[1]);
                   if(risposta == JOptionPane.OK_OPTION)
                    unitaOrganizzativa.accept(new RemoveChildrenVisitor(organigrammaPanel));
                }

                organigrammaPanel.remove(unitaOrganizzativa.getGraphicUnit());
                organigrammaPanel.setModified(true);

                organigrammaPanel.repaint();
                organigrammaPanel.revalidate();
            }
    }
}
