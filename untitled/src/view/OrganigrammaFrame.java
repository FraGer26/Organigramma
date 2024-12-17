package view;

import composite.GraphicUnit;
import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

public class OrganigrammaFrame extends JFrame {
    public final int HEIGHT = 600, WIDTH = 800;
    private OrganigrammaPanel organigrammaPanel;

    public OrganigrammaFrame() {
        organigrammaPanel = initPanel();
        initFrame();
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Avvolgi il pannello in uno JScrollPane
        JScrollPane scrollPane = new JScrollPane(organigrammaPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Aggiungi lo JScrollPane al JFrame
        add(scrollPane, BorderLayout.CENTER);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    private OrganigrammaPanel initPanel() {
        OrganigrammaPanel panel = new OrganigrammaPanel();
        panel.setLayout(null); // Layout null per un posizionamento manuale

        // Calcola automaticamente la dimensione preferita del pannello in base ai contenuti
        panel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                Dimension preferredSize = calculatePreferredSize(panel);
                panel.setPreferredSize(preferredSize);
                panel.revalidate();
            }
        });

        return panel;
    }

    private Dimension calculatePreferredSize(JPanel panel) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (Component component : panel.getComponents()) {
            Rectangle bounds = component.getBounds();
            maxWidth = Math.max(maxWidth, bounds.x + bounds.width);
            maxHeight = Math.max(maxHeight, bounds.y + bounds.height);
        }

        // Aggiungi un margine
        return new Dimension(maxWidth + 20, maxHeight + 20);
    }
}
