package visitor.draw;

import composite.ComponenteOrganizzativo;
import composite.UnitaOrganizzativa;
import visitor.Visitor;

import java.awt.*;
import java.util.List;

public class DrawLineVisitor implements Visitor {

    private final Graphics graphics; // Per disegnare le linee

    public DrawLineVisitor(Graphics graphics) {
        this.graphics = graphics;
    }


    @Override
    public void visit(UnitaOrganizzativa unita) {
        for (ComponenteOrganizzativo figlio : unita.getFigli()) {
            figlio.accept(this);
        }
        if(!unita.isRoot()) {

            Rectangle parentBounds = ((UnitaOrganizzativa)unita.getParent()).getGraphicUnit().getBounds();
            int parentX = parentBounds.x + parentBounds.width / 2; // Centro orizzontale
            int parentY = parentBounds.y + parentBounds.height;   // Base verticale
            Rectangle childBounds = unita.getGraphicUnit().getBounds();
            int childX = childBounds.x + childBounds.width / 2; // Centro orizzontale
            int childY = childBounds.y;                         // Top verticale
            graphics.drawLine(parentX, parentY, childX, childY);
        }

    }

}
