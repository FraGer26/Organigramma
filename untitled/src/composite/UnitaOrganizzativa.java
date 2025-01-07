// Percorso file: src/model/UnitaOrganizzativa.java
package composite;


import visitor.Visitor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UnitaOrganizzativa  implements ComponenteOrganizzativo, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private String nome;
    private final int altezza;
    private ComponenteOrganizzativo parent;
    private List<ComponenteOrganizzativo> figli=new ArrayList<>();
    private GraphicUnit graphicUnit;
    private boolean isRoot=false;
    private List<Role> Roles=new ArrayList<>();
    private List<Dipendente> Dipendenti=new ArrayList<>();

    public UnitaOrganizzativa(String nome, int altezza) {
        this.nome = nome;
        this.altezza = altezza;
    }

    public GraphicUnit getGraphicUnit() {
        return graphicUnit;
    }
    public void setGraphicUnit(GraphicUnit graphicUnit) {
        this.graphicUnit = graphicUnit;
    }

    public boolean isLeaf() {return figli.isEmpty();}

    @Override
    public boolean isRoot() {
        return isRoot;
    }

    @Override
    public void removeRole(Role role) {
        Roles.remove(role);
    }

    public void setRoot() {
        isRoot = true;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Role> getRoles() {
        return Roles;
    }
    public void addRole(Role role) {
        Roles.add(role);
    }

    public List<ComponenteOrganizzativo> getFigli() {
        return figli;
    }

    public void addFiglio(ComponenteOrganizzativo unita) {
        unita.setParent(this);
        figli.add(unita);

    }
    public void removeDipendente(Dipendente dipendente) {
        Dipendenti.remove(dipendente);
    }
    public Dipendente getDipendenteByNameAndSurnameAndRole(String name,String surname) {
        for (Dipendente dipendente : Dipendenti) {
            if (dipendente.getNome().equals(name) && dipendente.getSurname().equals(surname) ) {
                return dipendente;
            }
        }
        return null;
    }

    public void removeFiglio(ComponenteOrganizzativo unita) {
        figli.remove(unita);
    }

    public void setParent(ComponenteOrganizzativo parent) {
        this.parent = parent;
    }

    public ComponenteOrganizzativo getParent() {
        return parent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addDipendente(Dipendente dipendente) {
        Dipendenti.add(dipendente);
    }

    @Override
    public List<Dipendente> getDipendenti() {
        return Dipendenti;
    }

    public String toString()   {
        return getNome() ;
    }
    public int getHeight() {
        return altezza;
    }

}
