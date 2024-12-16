package view;

import composite.GraphicUnit;
import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

public class OrganigrammaFrame extends JFrame {
    public final int HEIGHT = 600, WIDTH = 800;
    private OrganigrammaPanel organigrammaPanel;
    public OrganigrammaFrame() {
        organigrammaPanel=initPanel();
       initFrame();
    }
    private void initFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JScrollPane(organigrammaPanel));

      //  setLayout(new BorderLayout());
        add(organigrammaPanel, BorderLayout.CENTER);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
    private OrganigrammaPanel initPanel() {

        OrganigrammaPanel panel = new OrganigrammaPanel();
        return panel;
    }



}