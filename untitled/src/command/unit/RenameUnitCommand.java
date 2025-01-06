package command.unit;

import command.Command;
import composite.GraphicUnit;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class RenameUnitCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public RenameUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }
    @Override
    public void execute() {
        String nameUnit = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if (nameUnit != null && !nameUnit.isEmpty()) {
            if(nameUnit.length()<= GraphicUnit.CHARACTER_LIMIT) {
                unitaOrganizzativa.setNome(nameUnit);
                organigrammaPanel.setModified(true);
                organigrammaPanel.repaint();
                organigrammaPanel.revalidate();
            }else{
                JOptionPane.showMessageDialog(null, "La nome inserito è troppo lungo,\nlunghezza massima: "+GraphicUnit.CHARACTER_LIMIT+" caratteri."
                        , "Inserisci nome", JOptionPane.ERROR_MESSAGE);
            }

        }

    }
}
