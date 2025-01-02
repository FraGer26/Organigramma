package command.unit;

import command.Command;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

import static composite.GraphicUnit.CHARACTER_LIMIT;

public class NewUnitCommand implements Command {
    private  OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public NewUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }

    public void execute() {

        String nomeFiglio = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if(nomeFiglio == null){return;}
        else if(nomeFiglio.length()>CHARACTER_LIMIT){
            JOptionPane.showMessageDialog(null,
                    "La lunghezza dei caratteri supera quella consentita",  // Messaggio di errore
                    "Errore lunghezza",                                           // Titolo della finestra
                    JOptionPane.ERROR_MESSAGE);                          // Tipo di messaggio
            return;
        } else if (nomeFiglio.length()<=0) {
            JOptionPane.showMessageDialog(null,
                    "non puoi creare una unita con un nome vuoto",  // Messaggio di errore
                    "Errore lunghezza",                                           // Titolo della finestra
                    JOptionPane.ERROR_MESSAGE);                          // Tipo di messaggio
            return;
        }

        int altezzaPadre = unitaOrganizzativa.getHeight();
        UnitaOrganizzativa figlio = new UnitaOrganizzativa(nomeFiglio, altezzaPadre + 1);
        GraphicUnit graphicUnitFiglio = new GraphicUnit(figlio, organigrammaPanel);
        graphicUnitFiglio.setVisible(true);

        figlio.setGraphicUnit(graphicUnitFiglio);
        unitaOrganizzativa.aggiungiFiglio(figlio);
        organigrammaPanel.add(graphicUnitFiglio);
        organigrammaPanel.setModified(true);

        organigrammaPanel.repaint();
        organigrammaPanel.revalidate();




    }

}
