package visitor;

import composite.ComponenteOrganizzativo;
import composite.Role;
import composite.UnitaOrganizzativa;

public class ExtendRoleVisitor implements Visitor{
    @Override
    public void visit(UnitaOrganizzativa unita) {
        for(ComponenteOrganizzativo child : unita.getFigli())
            if(child instanceof UnitaOrganizzativa)
                child.accept(this);
        if(!unita.isRoot) {
            for (Role role : ((UnitaOrganizzativa) unita.getParent()).getRoles()) {
                if (role.extend())
                    unita.getRoles().add(role);
                    

            }
        }
    }
}
