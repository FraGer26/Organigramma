package command.organigramma;

import command.Command;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static command.organigramma.SaveOrganigrammaCommand.mostraDialogoSalvataggio;

public class OpenOrganigrammaCommand implements Command {
    public OrganigrammaPanel organigrammaPanel;
    public OpenOrganigrammaCommand(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }
    @Override
    public void execute() {
        // Permette di caricare un organigramma da file
        int res=mostraDialogoSalvataggio(organigrammaPanel);
        if(res== JOptionPane.YES_OPTION) {
            new SaveOrganigrammaCommand(organigrammaPanel).execute();
        }
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(organigrammaPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object object = ois.readObject();
                if (object instanceof UnitaOrganizzativa) {
                    UnitaOrganizzativa root = (UnitaOrganizzativa) object;

                    organigrammaPanel.removeAll();

                    organigrammaPanel.setRootNode(root);
                    root.getGraphicUnit().setOrganigrammaPanel(organigrammaPanel);
                    organigrammaPanel.add(root.getGraphicUnit());


                    organigrammaPanel.repaint();
                    organigrammaPanel.revalidate();
                    JOptionPane.showMessageDialog(organigrammaPanel, "Organigramma caricato con successo.");
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(organigrammaPanel, "Errore durante il caricamento dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
