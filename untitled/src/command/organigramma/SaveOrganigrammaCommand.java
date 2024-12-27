package command.organigramma;

import command.Command;
import view.OrganigrammaPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveOrganigrammaCommand implements Command {
    public OrganigrammaPanel organigrammaPanel;
    public SaveOrganigrammaCommand(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }
    @Override
    public void execute() {
        // Permette di salvare l'organigramma su file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(organigrammaPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(organigrammaPanel.getRootNode());
                organigrammaPanel.setModified(false);
                JOptionPane.showMessageDialog(organigrammaPanel, "Organigramma salvato con successo.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(organigrammaPanel, "Errore durante il salvataggio dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   public static int mostraDialogoSalvataggio(OrganigrammaPanel organigrammaPanel) {
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
