package visitor;

import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.UnitaOrganizzativa;

import java.awt.*;
import java.util.List;

public class DrawLineVisitor implements Visitor {

    private Graphics graphics; // Per disegnare le linee

    public DrawLineVisitor(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void visitUnita(UnitaOrganizzativa unita) {
        if (!unita.getFigli().isEmpty()) {
            // Recupera il rettangolo del GraphicUnit del padre
            Rectangle parentBounds = unita.getGraphicUnit().getBounds();
            int parentX = parentBounds.x + parentBounds.width / 2; // Centro orizzontale
            int parentY = parentBounds.y + parentBounds.height;   // Base verticale

            // Disegna una linea per ogni figlio
            List<ComponenteOrganizzativo> figli = unita.getFigli();
            for (ComponenteOrganizzativo figlio : figli) {
                if (figlio instanceof UnitaOrganizzativa) {
                    // Recupera il rettangolo del GraphicUnit del figlio
                    Rectangle childBounds = ((UnitaOrganizzativa) figlio).getGraphicUnit().getBounds();
                    int childX = childBounds.x + childBounds.width / 2; // Centro orizzontale
                    int childY = childBounds.y;                         // Top verticale

                    // Disegna una linea tra il padre e il figlio
                    graphics.drawLine(parentX, parentY, childX, childY);

                    // Visita ricorsivamente il figlio
                    figlio.accept(this);
                }
            }
        }
    }

    @Override
    public void visitDipendente(Dipendente dipendente) {
        // Per ora non disegniamo linee per i Dipendenti
    }
}
