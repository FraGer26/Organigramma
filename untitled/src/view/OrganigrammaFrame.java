package view;

import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

public class OrganigrammaFrame extends JFrame {
    public final int HEIGHT = 600, WIDTH = 800;
    private OrganigrammaPanel panel;
    public OrganigrammaFrame() {
       panel=initPanel();
       initFrame();
    }
    private void initFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JScrollPane(panel));
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    private OrganigrammaPanel initPanel() {
        UnitaOrganizzativa root = new UnitaOrganizzativa("Node");
        OrganigrammaPanel panel = new OrganigrammaPanel(root);
        return panel;
    }


}