package command.unit;

import command.Command;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.Role;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class NewUnitCommand implements Command {
    private  OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public NewUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }

    public void execute() {


        String nomeFiglio = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if (nomeFiglio != null && !nomeFiglio.isEmpty()) {
            int altezzaPadre= unitaOrganizzativa.getHeight();
            UnitaOrganizzativa figlio = new UnitaOrganizzativa(nomeFiglio,altezzaPadre+1);
            GraphicUnit graphicUnitFiglio =new GraphicUnit(figlio,organigrammaPanel);
            graphicUnitFiglio.setVisible(true);

            figlio.setGraphicUnit(graphicUnitFiglio);
            unitaOrganizzativa.aggiungiFiglio(figlio);
            organigrammaPanel.add(graphicUnitFiglio);
            organigrammaPanel.setModified(true);
            System.out.println(unitaOrganizzativa.getFigli());

            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();


        }

    }

}
