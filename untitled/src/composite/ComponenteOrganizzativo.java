package composite;



import visitor.Visitor;

import java.io.Serializable;
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


}
