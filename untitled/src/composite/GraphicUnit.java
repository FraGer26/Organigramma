package composite;


import view.CompositeJPopupMenu;
import view.OrganigrammaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicUnit extends JComponent {
    private UnitaOrganizzativa unitaOrganizzativa;
    private  Rectangle bounds;
    public final int HEIGHT=50,WIDTH=100;

    public GraphicUnit(UnitaOrganizzativa unitaOrganizzativa,OrganigrammaPanel organigrammaPanel) {
        this.unitaOrganizzativa = unitaOrganizzativa;

        this.bounds = new Rectangle(400,0,WIDTH,HEIGHT);// Inizializza con dimensioni predefinite
        setBounds(bounds);


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



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Calcola il centro e le dimensioni del rettangolo

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
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        this.bounds = bounds;
    }



}
