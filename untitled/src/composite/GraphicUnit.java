package composite;

import Observer.SelectObserver;
import view.CompositeJPopupMenu;
import view.OrganigrammaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicUnit extends JComponent {
    private UnitaOrganizzativa unitaOrganizzativa;
    private final Rectangle bounds;
    private OrganigrammaPanel organigrammaPanel;
    public GraphicUnit(UnitaOrganizzativa unitaOrganizzativa) {
        this.unitaOrganizzativa = unitaOrganizzativa;
        this.bounds = new Rectangle(); // Inizializza il rettangolo


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (bounds.contains(e.getPoint())) {
                    update();
                    System.out.println("s");

                }
            }
        });
    }
    private void update(){
        organigrammaPanel.menu.update(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Calcola il centro e le dimensioni del rettangolo
        int larghezza = 100;
        int altezza = 50;
        int x = getWidth() / 2 - larghezza / 2;
        int y = getHeight() / 2 - altezza / 2;

        // Aggiorna il rettangolo di bounds
        bounds.setBounds(x, y, larghezza, altezza);

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
    private void disegnaUnita(Graphics g, UnitaOrganizzativa unita, int x, int y, int offset) {

    }
}
