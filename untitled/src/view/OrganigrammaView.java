package view;

import javax.swing.*;
import java.awt.*;

public class OrganigrammaView extends JFrame {
    private JTree organigrammaTree;
    private JButton aggiungiUnitaBtn, modificaUnitaBtn, rimuoviUnitaBtn;

    public OrganigrammaView() {
        setTitle("Gestione Organigramma Aziendale");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout principale
        setLayout(new BorderLayout());

        // Albero dell'organigramma
        organigrammaTree = new JTree();
        add(new JScrollPane(organigrammaTree), BorderLayout.CENTER);

        // Pannello pulsanti
        JPanel buttonPanel = new JPanel();
        aggiungiUnitaBtn = new JButton("Aggiungi Unità");
        modificaUnitaBtn = new JButton("Modifica Unità");
        rimuoviUnitaBtn = new JButton("Rimuovi Unità");

        buttonPanel.add(aggiungiUnitaBtn);
        buttonPanel.add(modificaUnitaBtn);
        buttonPanel.add(rimuoviUnitaBtn);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTree getOrganigrammaTree() {
        return organigrammaTree;
    }

    public JButton getAggiungiUnitaBtn() {
        return aggiungiUnitaBtn;
    }

    public JButton getModificaUnitaBtn() {
        return modificaUnitaBtn;
    }

    public JButton getRimuoviUnitaBtn() {
        return rimuoviUnitaBtn;
    }
}