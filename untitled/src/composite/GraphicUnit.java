package composite;


import view.CompositeJPopupMenu;
import view.OrganigrammaFrame;
import view.OrganigrammaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class GraphicUnit  extends JComponent {

    private UnitaOrganizzativa unitaOrganizzativa;
    private OrganigrammaPanel organigrammaPanel;
    private  Rectangle bounds;
    public static final int
            HEIGHT = 66, WIDTH = (int) (HEIGHT*2.5),
            CHARACTER_LIMIT = 20,
            VERTICAL_SPACE = HEIGHT, VERTICAL_OFFSET = 20,//Spazio e offset lasciato tra un nodo e un altro in verticale
            HORIZONTAL_SPACE = WIDTH/3, HORIZONTAL_OFFSET = 20;//Spazio e offset lasciato tra un nodo e un altro in orizzontale

    public GraphicUnit(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.organigrammaPanel=organigrammaPanel;
        this.bounds = new Rectangle(VERTICAL_OFFSET,0,WIDTH,HEIGHT);// Inizializza con dimensioni predefinite
        super.setBounds(bounds);

    }
    public void setOrganigrammaPanel(OrganigrammaPanel organigrammaPanel) {
        this.organigrammaPanel = organigrammaPanel;
    }
    public OrganigrammaPanel getOrganigrammaPanel() {
        return organigrammaPanel;
    }




    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        popupMenu();


        // Disegna il rettangolo
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH-1, HEIGHT-1);

        // Disegna il nome al centro del rettangolo
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(unitaOrganizzativa.getNome());
        int textX =  (WIDTH - textWidth) / 2;
        int textY = (HEIGHT + fm.getAscent()) / 2;
        g.drawString(unitaOrganizzativa.getNome(), textX, textY);
    }





    private void popupMenu(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (bounds.contains(e.getPoint())) {
                    // Crea il JPopupMenu
                    CompositeJPopupMenu popupMenu = new CompositeJPopupMenu(unitaOrganizzativa,organigrammaPanel);
                    // Mostra il popup nella posizione del clic
                    popupMenu.show(GraphicUnit.this, e.getX(), e.getY());


                }
            }
        });
    }


}
