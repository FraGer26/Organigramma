package command;

import javax.swing.*;

public class CommandJMenuItem extends JMenuItem {
    public CommandJMenuItem(String nome, Command command) {
        super(nome);
        addActionListener(e -> command.execute());
    }
}
