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
    public UnitaOrganizzativa root;
    private boolean modified = false;
    public OrganigrammaPanel() {
      initRootNode();
    }
    public void initRootNode(){
        root = new UnitaOrganizzativa("Nuova Unita");
        root.isRoot=true;
        setLayout(null);
        GraphicUnit gu=new GraphicUnit(root,this);
        root.setGraphicUnit(gu);
        add(gu);
    }
    public UnitaOrganizzativa getRootNode(){
        return root;
    }
    public void setRootNode(UnitaOrganizzativa root){
        this.root=root;
    }
    public void setModified(boolean modified){
        this.modified=modified;
    }
    public boolean isModified(){
        return modified;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ComponenteVisitor visitor=new ComponenteVisitor(this);
        root.accept(visitor);
        DrawLineVisitor lineVisitor = new DrawLineVisitor(g);
        root.accept(lineVisitor);


    }

}
