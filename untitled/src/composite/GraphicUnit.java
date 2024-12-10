package composite;

import javax.swing.*;
import java.awt.*;

public class GraphicUnit extends JComponent {
    private UnitaOrganizzativa unitaOrganizzativa;

    public GraphicUnit(UnitaOrganizzativa unitaOrganizzativa) {
        this.unitaOrganizzativa = unitaOrganizzativa;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

            disegnaUnita(g, unitaOrganizzativa, getWidth() / 2, 50, getWidth() / 4);

    }
    private void disegnaUnita(Graphics g, UnitaOrganizzativa unita, int x, int y, int offset) {
        int larghezza = 100;
        int altezza = 50;

        // Disegna il rettangolo
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x - larghezza / 2, y, larghezza, altezza);
        g.setColor(Color.BLACK);
        g.drawRect(x - larghezza / 2, y, larghezza, altezza);

        // Disegna il nome
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(unita.getNome());
        g.drawString(unita.getNome(), x - textWidth / 2, y + altezza / 2);

        // Salva la posizione


        // Disegna i figli
        int childY = y + 100;
        int childX = x - (unita.getFigli().size() - 1) * offset / 2;

        for (ComponenteOrganizzativo figlio : unita.getFigli()) {
            if(!figlio.isDipendente()) {
                g.drawLine(x, y + altezza, childX, childY);
                disegnaUnita(g, (UnitaOrganizzativa) figlio, childX, childY, offset / 2);
                childX += offset;
            }
        }
    }
}
