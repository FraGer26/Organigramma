package view;

import command.node.CommandNewUnit;
import composite.ComponenteOrganizzativo;
import composite.Dipendente;
import composite.GraphicUnit;
import composite.UnitaOrganizzativa;
import model.Role;
import visitor.ComponenteVisitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CompositeJPopupMenu extends JPopupMenu {
    UnitaOrganizzativa unitaOrganizzativo;
    OrganigrammaPanel organigrammaPanel;

    public CompositeJPopupMenu(UnitaOrganizzativa unitaOrganizzativa, OrganigrammaPanel organigrammaPanel) {
        this.unitaOrganizzativo = unitaOrganizzativa;
        this.organigrammaPanel = organigrammaPanel;
       mostraMenu(unitaOrganizzativa);

    }

    private void mostraMenu(UnitaOrganizzativa unita) {
        add(new CommandJMenuItem("Aggiungi Unità",new CommandNewUnit(unitaOrganizzativo,organigrammaPanel)));
        JMenuItem aggiungiDipendente = new JMenuItem("Aggiungi Dipendente");

        aggiungiDipendente.addActionListener(e -> {
            // Creare un pannello personalizzato con tre campi di input
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JTextField nomeField = new JTextField(10);
            JTextField cognomeField = new JTextField(10);  // Cambiato il nome del campo da stipendio a cognome
            JTextField stipendioField = new JTextField(10);

            // Creare il JComboBox per il ruolo
            JComboBox<String> ruoloComboBox = new JComboBox<>();

            // Ottieni i ruoli da unita.getRoles() e aggiungili al JComboBox
            for (Role ruolo : unita.getRoles()) {
                ruoloComboBox.addItem(ruolo.role());  // Aggiungi il nome del ruolo
            }

            // Aggiungere i componenti al pannello
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Cognome:"));
            panel.add(cognomeField);
            panel.add(new JLabel("Ruolo:"));
            panel.add(ruoloComboBox);

            // Mostrare il dialogo
            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Aggiungi Dipendente",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            // Gestire l'input
            if (result == JOptionPane.OK_OPTION) {
                String nome = nomeField.getText();
                String cognome = cognomeField.getText();
                String ruoloSelezionato = (String) ruoloComboBox.getSelectedItem();

                // Validazione semplice
                if (nome.isEmpty() || cognome.isEmpty()  || ruoloSelezionato == null) {
                    JOptionPane.showMessageDialog(null, "Tutti i campi sono obbligatori.", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Trova il ruolo corrispondente al nome selezionato
                    Role ruolo = null;
                    for (Role r : unita.getRoles()) {
                        if (r.role().equals(ruoloSelezionato)) {
                            ruolo = r;
                            break;
                        }
                    }
                    // Aggiungere il nuovo dipendente
                    Dipendente nuovoDipendente = new Dipendente(unita, nome, cognome, ruolo);
                    unita.aggiungiFiglio(nuovoDipendente); // Aggiungere il nuovo dipendente

                }
            }
        });



        JMenuItem gestisciDipendenti = new JMenuItem("Gestisci Dipendenti");
        gestisciDipendenti.addActionListener(e -> {
            mostraTabellaDipendenti(unita);
        });

        JMenuItem eliminaUnita = new JMenuItem("Elimina Unità");
        eliminaUnita.addActionListener(e -> {
            if (unita.getParent() != null) {
                unita.getParent().rimuoviFiglio(unita);
                repaint();
            }
        });

        JMenuItem aggiungiRuoli = new JMenuItem("aggiungi Ruoli");
        aggiungiRuoli.addActionListener(e -> {
            String nameRole = JOptionPane.showInputDialog("Inserisci il nome della nuova unità:");
            if (nameRole != null && !nameRole.isEmpty()) {
                unita.getRoles().add(new Role(nameRole,true));
                repaint();

            }

        });

        JMenuItem visualizzaRuoli = new JMenuItem("Visualizza Ruoli");
        visualizzaRuoli.addActionListener(e -> {
            mostraRuoli(unita);
        });


        add(eliminaUnita);
        addSeparator();
        add(aggiungiDipendente);
        add(gestisciDipendenti);
        addSeparator();
        add(aggiungiRuoli);
        add(visualizzaRuoli);
        JMenuItem test= new JMenuItem("Visualizza Ruoli");
        test.addActionListener(e -> {

            organigrammaPanel.repaint();
            organigrammaPanel.revalidate();
        });
        add( test);



    }
    private static void mostraTabellaDipendenti(UnitaOrganizzativa unita) {
        // Creare una lista di dipendenti (da sostituire con i tuoi dati reali)


        // Definire le colonne per la tabella
        String[] colonne = {"Nome", "Cognome", "Ruolo"};

        // Creare un modello di tabella con i dati dei dipendenti
        DefaultTableModel model = new DefaultTableModel(colonne, 0);

        // Aggiungere i dati dei dipendenti al modello della tabella
        for (ComponenteOrganizzativo componenteOrganizzativo : unita.getFigli()) {
            if(componenteOrganizzativo.isDipendente()) {
                Dipendente dipendente=(Dipendente) componenteOrganizzativo;
                Object[] row = {dipendente.getNome(), dipendente.getSurname(), dipendente.getRole().toString()};
                model.addRow(row);
            }
        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model);

        // Creare uno scroll pane per la tabella
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare una finestra per visualizzare la tabella
        JFrame tabellaFrame = new JFrame("Tabella Dipendenti");
        tabellaFrame.setSize(400, 300);
        tabellaFrame.add(scrollPane, BorderLayout.CENTER);
        tabellaFrame.setVisible(true);
    }
    private static void mostraRuoli(UnitaOrganizzativa unita) {
        // Creare una lista di voci (può essere qualsiasi lista di stringhe)


        // Definire il nome della colonna
        String[] colonne = {"Ruoli"};

        // Creare un modello di tabella con una sola colonna
        DefaultTableModel model = new DefaultTableModel(colonne, 0);


        // Aggiungere gli elementi della lista alla tabella
        for (Role role : unita.getRoles()) {
            model.addRow(new Object[]{role});

        }

        // Creare la tabella con il modello
        JTable tabella = new JTable(model);

        // Creare uno scroll pane per la tabella (così può scorrere se necessario)
        JScrollPane scrollPane = new JScrollPane(tabella);

        // Creare una finestra per visualizzare la tabella
        JFrame tabellaFrame = new JFrame("Tabella Ruoli");
        tabellaFrame.setSize(400, 300);
        tabellaFrame.add(scrollPane, BorderLayout.CENTER);
        tabellaFrame.setVisible(true);

    }



}
