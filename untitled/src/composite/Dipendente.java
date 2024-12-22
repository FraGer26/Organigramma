package composite;

import visitor.Visitor;

import java.io.Serializable;
import java.util.List;

public class Dipendente implements ComponenteOrganizzativo, Serializable {
    private ComponenteOrganizzativo parent;
    private String nome,surname;
    private Role role;

    public Dipendente(UnitaOrganizzativa parent, String nome,String Surname,Role role) {
        this.parent = parent;
        this.nome = nome;
        this.surname = Surname;
        this.role = role;
    }
    @Override
    public String getNome() {
        return nome;
    }
    public String getSurname() {
        return surname;
    }
    public Role getRole() {
        return role;
    }

    @Override
    public List<ComponenteOrganizzativo> getFigli() {
        throw new UnsupportedOperationException("Not supported getFigli from Dipendente");
    }

    @Override
    public void aggiungiFiglio(ComponenteOrganizzativo unita) {
        throw new UnsupportedOperationException("Not supported getFigli from Dipendente");
    }

    @Override
    public void rimuoviFiglio(ComponenteOrganizzativo unita) {
        throw new UnsupportedOperationException("Not supported getFigli from Dipendente");
    }

    @Override
    public void setParent(ComponenteOrganizzativo parent) {
        this.parent = parent;
    }

    @Override
    public ComponenteOrganizzativo getParent() {
        return parent;
    }
    @Override
    public boolean isDipendente() {
        return true;
    }
    public void accept(Visitor visitor) {
        visitor.visitDipendente(this);
    }
}
