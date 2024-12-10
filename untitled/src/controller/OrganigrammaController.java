package controller;

import model.Organigramma;
import view.OrganigrammaView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class OrganigrammaController {
    private Organigramma model;
    private OrganigrammaView view;

    public OrganigrammaController(Organigramma model, OrganigrammaView view) {
        this.model = model;
        this.view = view;

        initController();
    }

    private void initController() {
        view.getAggiungiUnitaBtn().addActionListener(e -> aggiungiUnita());
    }

    private void aggiungiUnita() {
        String nomeUnita = JOptionPane.showInputDialog("Inserisci il nome della nuova unità organizzativa:");
        if (nomeUnita != null && !nomeUnita.isEmpty()) {
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) view.getOrganigrammaTree().getModel().getRoot();
            rootNode.add(new DefaultMutableTreeNode(nomeUnita));
            ((DefaultTreeModel) view.getOrganigrammaTree().getModel()).reload();
        }
    }
}