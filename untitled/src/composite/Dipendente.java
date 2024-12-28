package composite;

import visitor.Visitor;

import java.io.Serializable;
import java.util.List;

public class Dipendente implements Serializable {

    private String nome, surname;
    private Role role;

    public Dipendente( String nome, String Surname, Role role) {

        this.nome = nome;
        this.surname = Surname;
        this.role = role;
    }


    public String getNome() {
        return nome;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }


}

