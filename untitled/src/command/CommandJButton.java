package command;

import javax.swing.*;

public class CommandJButton extends JButton {
    public CommandJButton(String nome, Command command) {
        super(nome);
        addActionListener(e ->command.execute());


    }
}
