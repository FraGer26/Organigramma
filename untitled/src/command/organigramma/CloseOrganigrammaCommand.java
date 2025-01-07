package command.organigramma;

import command.Command;
import view.OrganigrammaPanel;

import javax.swing.*;

import static command.organigramma.SaveOrganigrammaCommand.mostraDialogoSalvataggio;

public class CloseOrganigrammaCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;

    public CloseOrganigrammaCommand(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }
    @Override
    public void execute() {
        int res=mostraDialogoSalvataggio(organigrammaPanel);
        if(res== JOptionPane.YES_OPTION) {
            new SaveOrganigrammaCommand(organigrammaPanel).execute();
            System.exit(0);
        }else if (res==JOptionPane.NO_OPTION){
            System.exit(0);
        }
        return;

    }

}
