package command.organigramma;

import command.Command;
import view.OrganigrammaFrame;
import view.OrganigrammaPanel;

import javax.swing.*;

public class InfoOrganigrammaCommand implements Command {
    private OrganigrammaFrame organigrammaFrame;
    private int WIDTH=500, HEIGHT=200;
    public InfoOrganigrammaCommand(OrganigrammaFrame organigrammaFrame) {
        this.organigrammaFrame = organigrammaFrame;

    }
    @Override
    public void execute() {
        JDialog infoDialog = new JDialog(organigrammaFrame, "Informazioni di Aiuto", true);

        infoDialog.setSize(WIDTH, HEIGHT);
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
