package composite;



import visitor.Visitor;

import java.util.List;

public interface ComponenteOrganizzativo  {

    String getNome() ;
    List<ComponenteOrganizzativo> getFigli() ;
    void aggiungiFiglio(ComponenteOrganizzativo unita);
    void rimuoviFiglio(ComponenteOrganizzativo unita);
    void setParent(ComponenteOrganizzativo parent) ;
    boolean isDipendente() ;
    ComponenteOrganizzativo getParent() ;
    void accept(Visitor visitor);
    void addDipendente(Dipendente dipendente) ;
    void removeDipendente(Dipendente dipendente) ;
    List<Dipendente> getDipendenti() ;

    int getHeight();
}
