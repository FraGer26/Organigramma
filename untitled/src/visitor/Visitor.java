package visitor;

import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.UnitaOrganizzativa;

public interface Visitor {
    void visitUnita(UnitaOrganizzativa unita);
    void visitDipendente(Dipendente dipendente);

}
