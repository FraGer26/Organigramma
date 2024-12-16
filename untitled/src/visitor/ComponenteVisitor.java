package visitor;

import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ComponenteVisitor implements Visitor {

    private int yOffset = 100;  // Distanza verticale tra i livelli
    private int xOffset = 100;   // Distanza orizzontale minima tra i nodi

    @Override
    public void visitUnita(UnitaOrganizzativa unita) {
        // Ottieni il GraphicUnit del nodo corrente
        if(!unita.isRoot){

            UnitaOrganizzativa padre = (UnitaOrganizzativa) unita.getParent();
            Rectangle r=padre.getGraphicUnit().getBounds();
            unita.getGraphicUnit().setBounds(r.x+xOffset*padre.getFigli().indexOf(unita),r.y+yOffset , 100, 50);


            System.out.println(padre.getFigli().indexOf(unita));
            ArrayList<ComponenteOrganizzativo> figli=(ArrayList<ComponenteOrganizzativo>) padre.getFigli();
            System.out.println(Arrays.toString(figli.toArray()));
        }  for (ComponenteOrganizzativo co:unita.getFigli()){
            if(co instanceof UnitaOrganizzativa){
                co.accept(this);

            }
        }
    }

    @Override
    public void visitDipendente(Dipendente dipendente) {

    }
}