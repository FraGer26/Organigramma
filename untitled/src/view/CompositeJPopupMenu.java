package view;

import Observer.SelectObserver;
import composite.GraphicUnit;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Observable;

public class CompositeJPopupMenu extends JPopupMenu implements SelectObserver {
    private OrganigrammaPanel panel;
    private GraphicUnit currentUnit;
    public CompositeJPopupMenu() {
        this.panel=panel;

    }

    public void update(GraphicUnit currentUnit) {
        this.currentUnit=currentUnit;
        System.out.println("ciao");
    }

}
