package command.role;

import command.Command;
import command.CommandJButton;
import command.other.ManageRoleFrame;
import composite.UnitaOrganizzativa;
import composite.Role;
import view.OrganigrammaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageRoleCommand implements Command {

    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public ManageRoleCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }

    @Override
    public void execute() {
       new ManageRoleFrame(unitaOrganizzativa, organigrammaPanel);
    }
}
