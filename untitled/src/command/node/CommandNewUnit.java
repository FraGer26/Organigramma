package command.node;

import command.Command;
import composite.UnitaOrganizzativa;
import model.Organigramma;

public class CommandNewUnit implements Command {
    private  final Organigramma panel;
    public CommandNewUnit(Organigramma panel) {
        this.panel = panel;
    }
    public void execute() {
     /*   UnitaOrganizzativa selected = panel.getSelected();
        UnitaOrganizzativa nuovoFiglio = new UnitaOrganizzativa(nomeFiglio);
        selected.aggiungiFiglio(nuovoFiglio);
        panel.repaint();
        */

    }
}
