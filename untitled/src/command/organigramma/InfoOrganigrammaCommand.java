package command.organigramma;

import command.Command;
import view.OrganigrammaFrame;
import view.OrganigrammaPanel;

import javax.swing.*;

import static composite.GraphicUnit.CHARACTER_LIMIT;

public class InfoOrganigrammaCommand implements Command {
    private OrganigrammaFrame organigrammaFrame;
    private int WIDTH=600, HEIGHT=200;
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
                "<br>Le unità organizzative devono avere un nome con un numero di caratteri minore di "+CHARACTER_LIMIT+".</p></html>"));
        tabbedPane.addTab("Generale", tab1);

        JPanel tab2 = new JPanel();
        tab2.add(new JLabel(
                "<html><h3>Uso</h3>" +
                        "<p>click sia destro che sinistro. " +
                        "<br> su una unità permette di aggiungere un<br> una untà figlio all'unità selezionata."));
        tabbedPane.addTab("Scorciatoie", tab2);

        JPanel tab3 = new JPanel();
        tab3.add(new JLabel("<html><h3>About</h3><p>Creato da Francesco Gervino.</p></html>"));
        tabbedPane.addTab("About", tab3);

        infoDialog.add(tabbedPane);
        infoDialog.setVisible(true);
    }
}
