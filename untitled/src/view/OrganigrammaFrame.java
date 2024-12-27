package view;


import command.CommandJMenuItem;
import command.organigramma.*;
import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class OrganigrammaFrame extends JFrame {
    public static final int HEIGHT = 600, WIDTH = 2000;
    private OrganigrammaPanel organigrammaPanel;

    public OrganigrammaFrame() {
        organigrammaPanel = initPanel();
        initFrame();
        initMenu();
    }

    private void initFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Organigramma Aziendale");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new CloseOrganigrammaCommand(organigrammaPanel).execute();
            }
        });
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

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new CommandJMenuItem("Nuovo",new NewOrganigrammaCommand(organigrammaPanel)));
        fileMenu.add(new CommandJMenuItem("Apri",new OpenOrganigrammaCommand(organigrammaPanel)));
        fileMenu.add(new CommandJMenuItem("Save",new SaveOrganigrammaCommand(organigrammaPanel)));
        fileMenu.addSeparator();
        fileMenu.add(new CommandJMenuItem("Chiudi",new CloseOrganigrammaCommand(organigrammaPanel)));


        // Aggiungi il menu alla barra del menu
        menuBar.add(fileMenu);
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new CommandJMenuItem("info", new InfoOrganigrammaCommand(this)));
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }




}
