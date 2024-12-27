package command.organigramma;

import command.Command;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class NewOrganigrammaCommand implements Command {
    private OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    public NewOrganigrammaCommand(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    public void execute() {
        // Pulisce il pannello per creare un nuovo organigramma
        int res=mostraDialogoSalvataggio();
        if(res== JOptionPane.YES_OPTION) {
          //  salvaOrganigramma();
        } else if (res==JOptionPane.NO_OPTION) {
            organigrammaPanel.removeAll();
            organigrammaPanel.initRootNode();
            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();
            JOptionPane.showMessageDialog(organigrammaPanel, "Nuovo organigramma creato.");
        } else{

        }
    }
    public  int mostraDialogoSalvataggio() {
        if(organigrammaPanel.isModified()){
            return  JOptionPane.showConfirmDialog(null,
                    "Vuoi salvare le modifiche?",
                    "Conferma Salvataggio",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
        }
        return 1;
    }

}
