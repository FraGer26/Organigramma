package visitor.management;

import composite.ComponenteOrganizzativo;
import composite.Role;
import composite.UnitaOrganizzativa;
import visitor.Visitor;

public class RemoverExtendRoleVisitor implements Visitor {
    private Role role;
    public RemoverExtendRoleVisitor(Role role) {
        this.role = role;
    }
    @Override
    public void visit(UnitaOrganizzativa unita) {
        for(ComponenteOrganizzativo child : unita.getFigli())
            if(child instanceof UnitaOrganizzativa)
                child.accept(this);
        unita.getRoles().remove(role);
    }
}
