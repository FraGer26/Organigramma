package command.node;

import command.Command;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import model.Organigramma;
import view.OrganigrammaPanel;
import visitor.ComponenteVisitor;

import javax.swing.*;
import java.awt.*;

public class CommandNewUnit implements Command {
    private  final OrganigrammaPanel organigrammaPanel;
    private UnitaOrganizzativa unitaOrganizzativa;
    private static int off=100;
    public CommandNewUnit(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
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
            unitaOrganizzativa.getFigli().forEach(s->{ System.out.println("Nome Padre:"+unitaOrganizzativa.getNome()+"Lista figli:"+s.getNome());});

            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();


        }

    }
}
