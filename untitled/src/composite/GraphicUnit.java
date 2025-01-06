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
        this.bounds = new Rectangle((OrganigrammaFrame.WIDTH-WIDTH)/2,0,WIDTH,HEIGHT);// Inizializza con dimensioni predefinite
        setBounds(bounds);



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
        // Calcola il centro e le dimensioni del rettangolo
        popupMenu();
        int x = getWidth() / 2 - WIDTH / 2;
        int y = getHeight() / 2 - HEIGHT / 2;
        bounds=super.getBounds();
        // Aggiorna il rettangolo di bounds
        bounds.setBounds(x, y, WIDTH, HEIGHT);

        // Disegna il rettangolo
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        // Disegna il nome al centro del rettangolo
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(unitaOrganizzativa.getNome());
        int textX = bounds.x + (bounds.width - textWidth) / 2;
        int textY = bounds.y + (bounds.height + fm.getAscent()) / 2;
        g.drawString(unitaOrganizzativa.getNome(), textX, textY);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }
    @Override
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        this.bounds = bounds;
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
