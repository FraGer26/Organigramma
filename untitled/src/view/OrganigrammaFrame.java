package view;


import command.CommandJMenuItem;
import command.organigramma.*;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class OrganigrammaFrame extends JFrame {
    public static final int HEIGHT = 720, WIDTH = 1280;
    private OrganigrammaPanel organigrammaPanel;

    public OrganigrammaFrame() {
        organigrammaPanel = initPanel();
        initMenu();
        initFrame();

    }

    private void initFrame() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Organigramma Aziendale");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new CloseOrganigrammaCommand(organigrammaPanel).execute();
            }
        });
        setSize(WIDTH, HEIGHT);
        setVisible(true);

    }
    private OrganigrammaPanel initPanel() {
        OrganigrammaPanel panel = new OrganigrammaPanel();

        panel.setPreferredSize(new Dimension(GraphicUnit.WIDTH, GraphicUnit.HEIGHT));

        JScrollPane scrollPane = new CustomScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(GraphicUnit.WIDTH, GraphicUnit.HEIGHT));
        this.add(scrollPane, BorderLayout.CENTER);


        return panel;
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
