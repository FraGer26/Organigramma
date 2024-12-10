// Percorso file: src/model/UnitaOrganizzativa.java
package model;

import java.util.ArrayList;
import java.util.List;

public class UnitaOrganizzativa {
    private String nome;
    private UnitaOrganizzativa parent;
    private List<UnitaOrganizzativa> figli;

    public UnitaOrganizzativa(String nome) {
        this.nome = nome;
        this.figli = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<UnitaOrganizzativa> getFigli() {
        return figli;
    }

    public void aggiungiFiglio(UnitaOrganizzativa unita) {
        unita.setParent(this);
        figli.add(unita);
    }

    public void rimuoviFiglio(UnitaOrganizzativa unita) {
        figli.remove(unita);
    }

    public void setParent(UnitaOrganizzativa parent) {
        this.parent = parent;
    }

    public UnitaOrganizzativa getParent() {
        return parent;
    }
}
