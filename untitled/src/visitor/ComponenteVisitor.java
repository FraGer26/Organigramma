package visitor;

import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import java.awt.*;
import java.util.ArrayList;

public class ComponenteVisitor implements Visitor {

    private int yOffset = 100;  // Distanza verticale tra i livelli
    private int xOffset = 50;   // Distanza orizzontale minima tra i nodi
    private int subtreeSpacing = 20; // Spazio aggiuntivo tra sotto-alberi

    @Override
    public void visitUnita(UnitaOrganizzativa unita) {
        if (!unita.isRoot) {
            UnitaOrganizzativa padre = (UnitaOrganizzativa) unita.getParent();

            // Calcola la larghezza totale del sotto-albero del padre
            int totalWidth = calculateSubtreeWidth(padre);
            int parentX = padre.getGraphicUnit().getBounds().x;
            int parentY = padre.getGraphicUnit().getBounds().y;

            // Ottieni l'indice del figlio corrente
            ArrayList<ComponenteOrganizzativo> figli = (ArrayList<ComponenteOrganizzativo>) padre.getFigli();
            int index = figli.indexOf(unita);

            // Calcola la posizione orizzontale del figlio basandosi sulla larghezza dei sotto-alberi precedenti
            int currentOffset = calculateChildOffset(padre, index);
            int leftOffset = parentX - (totalWidth / 2) + currentOffset;

            // Imposta la posizione del nodo
            unita.getGraphicUnit().setBounds(leftOffset, parentY + yOffset, 100, 50);
        }

        // Visita ricorsivamente i figli
        for (ComponenteOrganizzativo co : unita.getFigli()) {
            if (co instanceof UnitaOrganizzativa) {
                co.accept(this);
            }
        }
    }

    @Override
    public void visitDipendente(Dipendente dipendente) {
        // Implementazione vuota per il momento
    }

    // Calcola la larghezza del sotto-albero di un nodo
    private int calculateSubtreeWidth(UnitaOrganizzativa unita) {
        if (unita.getFigli().isEmpty()) {
            return 100;  // Dimensione minima di un nodo foglia
        }

        int totalWidth = 0;
        for (ComponenteOrganizzativo co : unita.getFigli()) {
            if (co instanceof UnitaOrganizzativa) {
                totalWidth += calculateSubtreeWidth((UnitaOrganizzativa) co) + subtreeSpacing;
            }
        }
        return totalWidth;
    }

    // Calcola l'offset cumulativo per posizionare un figlio, basato sulla larghezza dei fratelli precedenti
    private int calculateChildOffset(UnitaOrganizzativa padre, int index) {
        int offset = 0;
        for (int i = 0; i < index; i++) {
            ComponenteOrganizzativo co = padre.getFigli().get(i);
            if (co instanceof UnitaOrganizzativa) {
                offset += calculateSubtreeWidth((UnitaOrganizzativa) co) + subtreeSpacing;
            }
        }
        return offset;
    }
}
