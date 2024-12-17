// Percorso file: src/view/OrganigrammaPanel.java
package view;


import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import composite.Role;
import visitor.ComponenteVisitor;
import visitor.DrawLineVisitor;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class OrganigrammaPanel extends JPanel {
    public   UnitaOrganizzativa root;
    public OrganigrammaPanel() {
      initRootNode();
    }
    private void initRootNode(){
        root = new UnitaOrganizzativa("test");
        root.isRoot=true;
        setLayout(null);
        GraphicUnit gu=new GraphicUnit(root,this);
        root.setGraphicUnit(gu);
        add(gu);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ComponenteVisitor visitor=new ComponenteVisitor();
        root.accept(visitor);
        DrawLineVisitor lineVisitor = new DrawLineVisitor(g);
        root.accept(lineVisitor);


    }

}
