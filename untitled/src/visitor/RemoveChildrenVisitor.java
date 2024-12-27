package visitor;

import composite.ComponenteOrganizzativo;
import composite.UnitaOrganizzativa;

import view.OrganigrammaPanel;


import javax.swing.*;

public class RemoveChildrenVisitor implements Visitor {
    /**
     * Rimuove tutti gli oggetti <code>GraphicNode</code> dei nodi figli del node su cui si invoca la accept() dal panel.
     */
    private final OrganigrammaPanel organigrammaPanel;
    public RemoveChildrenVisitor(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }

    @Override
    public void visit(UnitaOrganizzativa node) {

        for(ComponenteOrganizzativo child : node.getFigli())
            if(child instanceof UnitaOrganizzativa)
             child.accept(this);
        organigrammaPanel.remove(node.getGraphicUnit());
    }
}
