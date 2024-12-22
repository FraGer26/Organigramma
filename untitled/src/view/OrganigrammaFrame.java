package view;

import composite.GraphicUnit;
import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class OrganigrammaFrame extends JFrame {
    public final int HEIGHT = 600, WIDTH = 800;
    private OrganigrammaPanel organigrammaPanel;

    public OrganigrammaFrame() {
        organigrammaPanel = initPanel();
        initFrame();
        initMenu();
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

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem nuovoItem = new JMenuItem("Nuovo");
        JMenuItem apriItem = new JMenuItem("Apri");
        JMenuItem salvaItem = new JMenuItem("Salva");
        JMenuItem chiudiItem = new JMenuItem("Chiudi");

        // Aggiungi listener per le azioni del menu
        nuovoItem.addActionListener(e -> nuovoOrganigramma());
        apriItem.addActionListener(e -> apriOrganigramma());
        salvaItem.addActionListener(e -> salvaOrganigramma());
        chiudiItem.addActionListener(e -> System.exit(0));

        // Aggiungi gli elementi al menu File
        fileMenu.add(nuovoItem);
        fileMenu.add(apriItem);
        fileMenu.add(salvaItem);
        fileMenu.addSeparator();
        fileMenu.add(chiudiItem);

        // Aggiungi il menu alla barra del menu
        menuBar.add(fileMenu);

        // Imposta la barra del menu sul frame
        setJMenuBar(menuBar);
    }

    private void nuovoOrganigramma() {
        // Pulisce il pannello per creare un nuovo organigramma
        organigrammaPanel.removeAll();
        organigrammaPanel.initRootNode();
        organigrammaPanel.repaint();
        JOptionPane.showMessageDialog(this, "Nuovo organigramma creato.");
    }

    private void apriOrganigramma() {
        // Permette di caricare un organigramma da file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object object = ois.readObject();
                if (object instanceof UnitaOrganizzativa) {
                    organigrammaPanel.setRootNode((UnitaOrganizzativa) object);
                    System.out.println(organigrammaPanel.getRootNode().getFigli());
                    for (UnitaOrganizzativa uo:organigrammaPanel.getRootNode().getFigli()){

                    }

                    organigrammaPanel.repaint();
                    organigrammaPanel.revalidate();
                    JOptionPane.showMessageDialog(this, "Organigramma caricato con successo.");
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Errore durante il caricamento dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvaOrganigramma() {
        // Permette di salvare l'organigramma su file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(organigrammaPanel.getRootNode());
                JOptionPane.showMessageDialog(this, "Organigramma salvato con successo.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
