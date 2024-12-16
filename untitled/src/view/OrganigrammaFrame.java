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

        // Imposta una dimensione preferita per il pannello
        panel.setPreferredSize(new Dimension(2000, 1000)); // Modifica le dimensioni secondo il contenuto
        panel.setLayout(null); // Layout null per un posizionamento manuale
        return panel;
    }
}
