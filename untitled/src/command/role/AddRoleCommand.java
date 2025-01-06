package command.role;

import command.Command;
import composite.UnitaOrganizzativa;
import composite.Role;
import view.OrganigrammaPanel;

import javax.swing.*;

public class AddRoleCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public AddRoleCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        String nameRole = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");

        if (nameRole != null && !nameRole.isEmpty()) {
            unitaOrganizzativa.addRole(nameDialog(nameRole));
            organigrammaPanel.setModified(true);
            organigrammaPanel.repaint();

        }
    }


    private Role nameDialog(String nameRole) {

        // Creazione di un JCheckBox
        JCheckBox checkBox = new JCheckBox("Aggiungi il ruolo alle sotto unita");

        // Creazione di un JPanel per contenere la checkbox
        JPanel panel = new JPanel();
        panel.add(checkBox);

        // Mostra una finestra di dialogo con la checkbox
        JOptionPane.showConfirmDialog(null, panel,
                "Eredita Ruolo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        // Controlla il risultato e se la checkbox è selezionata

        return new Role(nameRole,checkBox.isSelected());
    }
}


