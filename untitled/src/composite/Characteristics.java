package composite;

import java.io.Serial;
import java.io.Serializable;

public record Characteristics(String name, String surname, Role role) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

}
