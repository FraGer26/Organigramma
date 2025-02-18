package visitor.management;

import composite.ComponenteOrganizzativo;
import composite.Role;
import composite.UnitaOrganizzativa;
import visitor.Visitor;

public class ExtendRoleVisitor implements Visitor {
    @Override
    public void visit(UnitaOrganizzativa unita) {
        for(ComponenteOrganizzativo child : unita.getFigli())
                child.accept(this);
        if(!unita.isRoot()) {
            for (Role role : ((UnitaOrganizzativa) unita.getParent()).getRoles()) {
                if (role.extend() && !unita.getRoles().contains(role))
                    unita.addRole(role);
            }
        }
    }
}
