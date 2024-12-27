package command.organigramma;

import command.Command;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

import static command.organigramma.SaveOrganigrammaCommand.mostraDialogoSalvataggio;

public class NewOrganigrammaCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;

    public NewOrganigrammaCommand(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }
    @Override
    public void execute() {
        // Pulisce il pannello per creare un nuovo organigramma
        int res=mostraDialogoSalvataggio(organigrammaPanel);
        if(res== JOptionPane.YES_OPTION) {new SaveOrganigrammaCommand(organigrammaPanel).execute();
        } else if (res==JOptionPane.NO_OPTION) {
            organigrammaPanel.removeAll();
            organigrammaPanel.initRootNode();
            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();
            JOptionPane.showMessageDialog(organigrammaPanel, "Nuovo organigramma creato.");
        } else{

        }
    }


}
