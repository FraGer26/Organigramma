package command.unit;

import command.Command;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class NewUnitCommand implements Command {
    private  final OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;

    public NewUnitCommand(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
        this.unitaOrganizzativa = unitaOrganizzativa;

    }
    public void execute() {


        String nomeFiglio = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
        if (nomeFiglio != null && !nomeFiglio.isEmpty()) {

            UnitaOrganizzativa figlio = new UnitaOrganizzativa(nomeFiglio);
            GraphicUnit graphicUnitFiglio =new GraphicUnit(figlio,organigrammaPanel);
            graphicUnitFiglio.setVisible(true);

            figlio.setGraphicUnit(graphicUnitFiglio);
            unitaOrganizzativa.aggiungiFiglio(figlio);
            organigrammaPanel.add(graphicUnitFiglio);
         //   unitaOrganizzativa.getFigli().forEach(s->{ System.out.println("Nome Padre:"+unitaOrganizzativa.getNome()+"Lista figli:"+s.getNome());});

            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();


        }

    }
}
