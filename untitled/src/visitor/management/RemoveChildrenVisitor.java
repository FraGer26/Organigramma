package visitor.management;

import composite.ComponenteOrganizzativo;
import composite.UnitaOrganizzativa;

import view.OrganigrammaPanel;
import visitor.Visitor;

public class RemoveChildrenVisitor implements Visitor {

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
