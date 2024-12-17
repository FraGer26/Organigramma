package command;

import javax.swing.*;

public class CommandJButton extends JButton {
    public CommandJButton(String nome, Command command,JPanel panel) {
        super(nome);
        addActionListener(e ->{ command.execute();
            panel.repaint();
            panel.revalidate();});


    }
}
