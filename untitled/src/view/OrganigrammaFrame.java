package view;


import composite.UnitaOrganizzativa;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                chiudiOrganigramma();
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
        JMenuItem nuovoItem = new JMenuItem("Nuovo");
        JMenuItem apriItem = new JMenuItem("Apri");
        JMenuItem salvaItem = new JMenuItem("Salva");
        JMenuItem chiudiItem = new JMenuItem("Chiudi");

        // Aggiungi listener per le azioni del menu
        nuovoItem.addActionListener(e -> nuovoOrganigramma());
        apriItem.addActionListener(e -> apriOrganigramma());
        salvaItem.addActionListener(e -> salvaOrganigramma());
        chiudiItem.addActionListener(e -> chiudiOrganigramma());

        // Aggiungi gli elementi al menu File
        fileMenu.add(nuovoItem);
        fileMenu.add(apriItem);
        fileMenu.add(salvaItem);
        fileMenu.addSeparator();
        fileMenu.add(chiudiItem);

        // Aggiungi il menu alla barra del menu
        menuBar.add(fileMenu);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem infoItem = new JMenuItem("info");
        helpMenu.add(infoItem);
        infoItem.addActionListener(e -> showInfoDialog());
        menuBar.add(helpMenu);
        // Imposta la barra del menu sul frame
        setJMenuBar(menuBar);
    }
    private void chiudiOrganigramma() {
        int res=mostraDialogoSalvataggio();
        if(res==JOptionPane.YES_OPTION) {
            salvaOrganigramma();
        }else if (res==JOptionPane.NO_OPTION){
            System.exit(0);
        }else{

        }

    }

    private void nuovoOrganigramma() {
        // Pulisce il pannello per creare un nuovo organigramma
        int res=mostraDialogoSalvataggio();
        if(res==JOptionPane.YES_OPTION) {
            salvaOrganigramma();
        } else if (res==JOptionPane.NO_OPTION) {
            organigrammaPanel.removeAll();
            organigrammaPanel.initRootNode();
            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();
            JOptionPane.showMessageDialog(this, "Nuovo organigramma creato.");
        } else{

        }
    }
    public  int mostraDialogoSalvataggio() {
       if(organigrammaPanel.isModified()){
         return  JOptionPane.showConfirmDialog(null,
                   "Vuoi salvare le modifiche?",
                   "Conferma Salvataggio",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.QUESTION_MESSAGE);
       }
       return 1;
    }

    private void apriOrganigramma() {
        // Permette di caricare un organigramma da file
        int res=mostraDialogoSalvataggio();
        if(res==JOptionPane.YES_OPTION) {
            salvaOrganigramma();
            }
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object object = ois.readObject();
                if (object instanceof UnitaOrganizzativa) {
                    UnitaOrganizzativa root = (UnitaOrganizzativa) object;

                    organigrammaPanel.removeAll();

                    organigrammaPanel.setRootNode(root);
                    root.getGraphicUnit().setOrganigrammaPanel(organigrammaPanel);
                    organigrammaPanel.add(root.getGraphicUnit());


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
                organigrammaPanel.setModified(false);
                JOptionPane.showMessageDialog(this, "Organigramma salvato con successo.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Errore durante il salvataggio dell'organigramma.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showInfoDialog() {
        JDialog infoDialog = new JDialog(this, "Informazioni di Aiuto", true);
        infoDialog.setSize(WIDTH/3, HEIGHT/3);
        infoDialog.setLocationRelativeTo(null);
        infoDialog.setResizable(false);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("<html><h3>Generale</h3>" +
                "<p>L'applicazione permette di gestire un organigramma aziendale. " +
                "<br>Non c'è un limite in altezza o larghezza per l'organigramma." +
                "<br>I nodi devono avere un nome con un numero di caratteri minore di "+"-----"+".</p></html>"));
        tabbedPane.addTab("Generale", tab1);

        JPanel tab2 = new JPanel();
        tab2.add(new JLabel(
                "<html>" +
                        "<h3>Scorciatoie</h3>" +
                        "<ol>"+
                        "<li><b>Doppio click</b>, permette di aggiungere un<br> nodo figlio al nodo cliccato.</li>" +
                        "<li><b>Tasto centrale del mouse</b>, permette di rinominare<br> il nodo selezionato.</li>" +
                        "</ol>"+
                        "</html>"));
        tabbedPane.addTab("Scorciatoie", tab2);

        JPanel tab3 = new JPanel();
        tab3.add(new JLabel("<html><h3>About</h3><p>Creato da Francesco Gervino.</p></html>"));
        tabbedPane.addTab("About", tab3);

        infoDialog.add(tabbedPane);
        infoDialog.setVisible(true);
    }

}
