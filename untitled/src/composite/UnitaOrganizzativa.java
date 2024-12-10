// Percorso file: src/model/UnitaOrganizzativa.java
package composite;

import model.Role;

import java.util.ArrayList;
import java.util.List;

public class UnitaOrganizzativa  implements ComponenteOrganizzativo {
    private String nome;
    private ComponenteOrganizzativo parent;
    private List<ComponenteOrganizzativo> figli;
    private GraphicUnit graphicUnit;
    private List<Role> Roles=new ArrayList<>();

    public UnitaOrganizzativa(String nome) {
        this.nome = nome;
        this.figli = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public List<Role> getRoles() {
        return Roles;
    }

    public List<ComponenteOrganizzativo> getFigli() {
        return figli;
    }

    public void aggiungiFiglio(ComponenteOrganizzativo unita) {
        unita.setParent(this);
        figli.add(unita);

    }

    public void rimuoviFiglio(ComponenteOrganizzativo unita) {
        figli.remove(unita);
    }

    public void setParent(ComponenteOrganizzativo parent) {
        this.parent = parent;
    }

    public ComponenteOrganizzativo getParent() {
        return parent;
    }
    public boolean isDipendente() {
        return false;
    }
}
