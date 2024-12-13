package visitor;

import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

public class ComponenteVisitor implements Visitor {

    public ComponenteVisitor() {

    }
    @Override
    public void visitUnita(UnitaOrganizzativa unita) {


      //  unita.getFigli().forEach(f -> f.accept(this));



    }

    @Override
    public void visitDipendente(Dipendente dipendente) {

    }
}
