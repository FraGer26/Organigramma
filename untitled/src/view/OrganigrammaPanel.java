// Percorso file: src/view/OrganigrammaPanel.java
package view;

import model.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class OrganigrammaPanel extends JPanel {
    private UnitaOrganizzativa root;
    private Map<UnitaOrganizzativa, Rectangle> posizioni; // Per tracciare le posizioni dei nodi

    public OrganigrammaPanel(UnitaOrganizzativa root) {
        this.root = root;
        this.posizioni = new HashMap<>();
        this.setBackground(Color.WHITE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Map.Entry<UnitaOrganizzativa, Rectangle> entry : posizioni.entrySet()) {
                    if (entry.getValue().contains(e.getPoint())) {
                        mostraMenu(entry.getKey(), e.getPoint());
                        break;
                    }
                }
            }
        });
    }

    private void mostraMenu(UnitaOrganizzativa unita, Point punto) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem aggiungiFiglio = new JMenuItem("Aggiungi Sotto-Unita");
        aggiungiFiglio.addActionListener(e -> {
            String nomeFiglio = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
            if (nomeFiglio != null && !nomeFiglio.isEmpty()) {
                UnitaOrganizzativa nuovoFiglio = new UnitaOrganizzativa(nomeFiglio);
                unita.aggiungiFiglio(nuovoFiglio);
                repaint();
            }
        });

        JMenuItem eliminaUnita = new JMenuItem("Elimina Unità");
        eliminaUnita.addActionListener(e -> {
            if (unita.getParent() != null) {
                unita.getParent().rimuoviFiglio(unita);
                repaint();
            }
        });

        menu.add(aggiungiFiglio);
        menu.add(eliminaUnita);
        menu.show(this, punto.x, punto.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (root != null) {
            posizioni.clear();
            disegnaUnita(g, root, getWidth() / 2, 50, getWidth() / 4);
        }
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
        posizioni.put(unita, new Rectangle(x - larghezza / 2, y, larghezza, altezza));

        // Disegna i figli
        int childY = y + 100;
        int childX = x - (unita.getFigli().size() - 1) * offset / 2;

        for (UnitaOrganizzativa figlio : unita.getFigli()) {
            g.drawLine(x, y + altezza, childX, childY);
            disegnaUnita(g, figlio, childX, childY, offset / 2);
            childX += offset;
        }
    }
}
