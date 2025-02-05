package visitor.draw;

import composite.ComponenteOrganizzativo;
import composite.UnitaOrganizzativa;
import view.OrganigrammaPanel;
import visitor.Visitor;

import static composite.GraphicUnit.*;

public class ComponenteVisitor implements Visitor {

    private int height, width;
    private int xLeaf = HORIZONTAL_OFFSET;    // Offset orizzontale iniziale per la posizione dei nodi foglia
    private final OrganigrammaPanel organigrammaPanel;
    public ComponenteVisitor(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }

    @Override
    public void visit(UnitaOrganizzativa unita) {
        // Visita ricorsivamente i figli
        for (ComponenteOrganizzativo co : unita.getFigli())
            co.accept(this);

        loadUpdate(unita);
        if(unita.isLeaf()) width++;
        height = Math.max(unita.getHeight(), height);
            // Calcolo la posizione del nodo
        if (unita.isLeaf() ) {
                // Se è una foglia, assegno una posizione orizzontale basata su xLeaf
                unita.getGraphicUnit().setBounds(xLeaf, getY(unita), unita.getGraphicUnit().getBounds().width, unita.getGraphicUnit().getBounds().height);
                xLeaf += WIDTH+HORIZONTAL_SPACE; // Aumento xLeaf per il prossimo nodo foglia

        } else {
                // Se non è una foglia, calcolo la posizione in base ai figli
            if (unita.getFigli().size() == 1) {
                    // Se ha un solo figlio, la sua posizione sarà la stessa del figlio
                int xChild = ((UnitaOrganizzativa)unita.getFigli().get(0)).getGraphicUnit().getBounds().x;

                    unita.getGraphicUnit().setBounds(xChild, getY(unita), unita.getGraphicUnit().getBounds().width, unita.getGraphicUnit().getBounds().height);
            } else {
                    // Se ha più figli, calcolo la media tra il primo e l'ultimo figlio
                    int xParent = getXParent(unita);
                    unita.getGraphicUnit().setBounds(xParent, getY(unita), unita.getGraphicUnit().getBounds().width, unita.getGraphicUnit().getBounds().height);

            }
            }

    }

    private void loadUpdate(UnitaOrganizzativa unita) {
        if (!unita.isRoot() && ((UnitaOrganizzativa) unita.getParent()).getGraphicUnit().getOrganigrammaPanel() != unita.getGraphicUnit().getOrganigrammaPanel()) {
            unita.getGraphicUnit().setOrganigrammaPanel(((UnitaOrganizzativa) unita.getParent()).getGraphicUnit().getOrganigrammaPanel());
            organigrammaPanel.add(unita.getGraphicUnit());
        }
    }



    // Calcola la posizione X media tra il primo e l'ultimo figlio
    private int getXParent(UnitaOrganizzativa unita) {

            int xFirst = ((UnitaOrganizzativa) unita.getFigli().get(0)).getGraphicUnit().getBounds().x;
            int xLast = ((UnitaOrganizzativa) unita.getFigli().get(unita.getFigli().size()-1)).getGraphicUnit().getBounds().x;
            return xFirst + (xLast - xFirst) / 2;


    }

    // Calcola la posizione Y di un nodo in base alla sua profondità
    private int getY(UnitaOrganizzativa unita) {
        return unita.getHeight()* (VERTICAL_SPACE+HEIGHT) + VERTICAL_OFFSET; // Y posizionato in base alla profondità
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
