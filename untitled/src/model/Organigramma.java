// Percorso file: src/model/Organigramma.java
package model;

import composite.UnitaOrganizzativa;

public class Organigramma {
    private UnitaOrganizzativa root;

    public Organigramma(String nomeRoot) {
        this.root = new UnitaOrganizzativa(nomeRoot);
    }

    public UnitaOrganizzativa getRoot() {
        return root;
    }
}
