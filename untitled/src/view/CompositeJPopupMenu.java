package view;

import command.CommandJMenuItem;
import command.role.AddRoleCommand;
import command.role.ShowRoleCommand;
import command.dipendenti.AddDipendentiCommand;
import command.dipendenti.ShowDipendentiCommand;
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
        add(new CommandJMenuItem("Rimuovi Unità",new RemoveUnitCommand(unitaOrganizzativo,organigrammaPanel)));
        addSeparator();
        add(new CommandJMenuItem("Aggiungi Ruoli",new AddRoleCommand(unitaOrganizzativo,organigrammaPanel)));
        add(new CommandJMenuItem("Mostra Ruoli",new ShowRoleCommand(unitaOrganizzativo,organigrammaPanel)));
        addSeparator();
        add(new CommandJMenuItem("Aggiungi Dipendenti",new AddDipendentiCommand(unitaOrganizzativo,organigrammaPanel)));
        add(new CommandJMenuItem("Mostra Dipendeti",new ShowDipendentiCommand(unitaOrganizzativo,organigrammaPanel)));



    }





}
