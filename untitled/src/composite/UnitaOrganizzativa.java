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
    private List<ComponenteOrganizzativo> figli;
    private GraphicUnit graphicUnit;
    public boolean isRoot=false;
    private List<Role> Roles=new ArrayList<>();
    private List<Dipendente> Dipendentes=new ArrayList<>();

    public UnitaOrganizzativa(String nome, int altezza) {
        this.nome = nome;
        this.altezza = altezza;
        this.figli = new ArrayList<>();
    }

    public GraphicUnit getGraphicUnit() {
        return graphicUnit;
    }
    public void setGraphicUnit(GraphicUnit graphicUnit) {
        this.graphicUnit = graphicUnit;
    }


    public boolean isLeaf() {return figli.isEmpty();}
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
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
    public void removeDipendente(Dipendente dipendente) {
        Dipendentes.remove(dipendente);
    }
    public Dipendente getDipendenteByName(String name) {
        for (Dipendente dipendente : Dipendentes) {
            if (dipendente.getNome().equals(name)) {
                return dipendente;
            }
        }
        return null;
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addDipendente(Dipendente dipendente) {
        Dipendentes.add(dipendente);
    }

    @Override
    public List<Dipendente> getDipendentes() {
        return Dipendentes;
    }



    public String toString()   {
        return getNome() ;
    }
    public int getHeight() {
        return altezza;
    }

}
