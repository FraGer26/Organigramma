package view;

import command.CommandJMenuItem;
import command.role.AddRoleCommand;
import command.role.ManageRoleCommand;
import command.dipendenti.AddDipendentiCommand;
import command.dipendenti.ManageDipendentiCommand;
import command.unit.*;
import composite.UnitaOrganizzativa;

import javax.swing.*;

public class CompositeJPopupMenu extends JPopupMenu {
    UnitaOrganizzativa unitaOrganizzativo;
    OrganigrammaPanel organigrammaPanel;

    public CompositeJPopupMenu(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.unitaOrganizzativo = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
        setupMenu();

    }

    private void setupMenu() {


        add(new CommandJMenuItem("Aggiungi Unità",new NewUnitCommand(unitaOrganizzativo,organigrammaPanel)));
        add(new CommandJMenuItem("Rinomina Unità",new RenameUnitCommand(unitaOrganizzativo,organigrammaPanel)));
        add(new CommandJMenuItem("Rimuovi Unità",new RemoveUnitCommand(unitaOrganizzativo,organigrammaPanel)));
        addSeparator();
        add(new CommandJMenuItem("Gestisci Dipendeti",new ManageDipendentiCommand(unitaOrganizzativo,organigrammaPanel)));
        addSeparator();
        add(new CommandJMenuItem("Gestisci Ruoli",new ManageRoleCommand(unitaOrganizzativo,organigrammaPanel)));


    }





}
