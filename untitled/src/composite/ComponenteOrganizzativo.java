package composite;



import visitor.Visitor;

import java.util.List;

public interface ComponenteOrganizzativo  {

    String getNome() ;
    List<ComponenteOrganizzativo> getFigli() ;
    void addFiglio(ComponenteOrganizzativo cmp);
    void removeFiglio(ComponenteOrganizzativo cmp);
    void setParent(ComponenteOrganizzativo parent) ;
    void addRole(Role role);
    ComponenteOrganizzativo getParent() ;
    void accept(Visitor visitor);
    void addDipendente(Dipendente dipendente) ;
    void removeDipendente(Dipendente dipendente) ;
    List<Dipendente> getDipendenti() ;
    int getHeight();
    boolean isLeaf();
    boolean isRoot();
    void removeRole(Role role) ;
}
