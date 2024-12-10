// Percorso file: src/Main.java
import model.UnitaOrganizzativa;
import view.OrganigrammaPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Creazione del modello
            UnitaOrganizzativa root = new UnitaOrganizzativa("Consiglio di Amministrazione");
            UnitaOrganizzativa comitato = new UnitaOrganizzativa("Comitato Tecnico Scientifico");
            UnitaOrganizzativa ricerca = new UnitaOrganizzativa("Ricerca e Sviluppo");
            root.aggiungiFiglio(comitato);
            comitato.aggiungiFiglio(ricerca);

            // Creazione della vista
            OrganigrammaPanel panel = new OrganigrammaPanel(root);

            // Creazione della finestra
            JFrame frame = new JFrame("Organigramma Aziendale");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new JScrollPane(panel));
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
