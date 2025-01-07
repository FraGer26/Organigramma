package composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.OrganigrammaPanel;
import visitor.draw.ComponenteVisitor;
import visitor.management.ExtendRoleVisitor;

import static composite.GraphicUnit.*;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private UnitaOrganizzativa root, figlio1, figlio2, figlio3;
    private GraphicUnit gRoot, gfiglio1, gfiglio2, gfiglio3;
    private OrganigrammaPanel panel;
    private Dipendente dipendente1, dipendente2;
    private Role role1, role2;
    @BeforeEach
    void methodToTest() {
        panel = new OrganigrammaPanel();

        root = new UnitaOrganizzativa("radice",0);
        root.setRoot();
        figlio1 = new UnitaOrganizzativa("Figlio 1", 1);
        figlio2 = new UnitaOrganizzativa("Figlio 2", 2);
        figlio3 = new UnitaOrganizzativa("Figlio 3", 1);

        gRoot=new GraphicUnit(root,panel);
        gfiglio1=new GraphicUnit(figlio1,panel);
        gfiglio2=new GraphicUnit(figlio2,panel);
        gfiglio3=new GraphicUnit(figlio3,panel);
        root.setGraphicUnit(gRoot);

        dipendente1=new Dipendente("Francesco","Gervino",new Role("Amministratore",false));
        dipendente2=new Dipendente("Emanuela","Adriani",new Role("Tecnico",false));
        role1=new Role("Amministratore",false);
        role2=new Role("Tecnico",true);


    }
    @Test
    public void testAddAndRemoveFiglio() {
        root.addFiglio(figlio1);
        assertEquals(1,root.getFigli().size());

        root.removeFiglio(figlio1);
        assertEquals(0,root.getFigli().size());
    }
    @Test
    public void testIsLeaf(){
        assertTrue(root.isLeaf());
        root.addFiglio(figlio1);
        assertFalse(root.isLeaf());

    }
    @Test
    public void testIsRoot(){
        assertTrue(root.isRoot());
        assertFalse(figlio1.isRoot());
    }

    @Test
    public void testGetFigli() {
        root.addFiglio(figlio1);
        root.addFiglio(figlio2);
        assertTrue(root.getFigli().contains(figlio1));
        assertTrue(root.getFigli().contains(figlio2));
        assertEquals(2,root.getFigli().size());
    }
    @Test
    public void testSetAndGetGraphicUnit(){
        figlio1.setGraphicUnit(gfiglio1);
        assertEquals(gfiglio1,figlio1.getGraphicUnit());
        assertEquals(gRoot,root.getGraphicUnit());
    }
    @Test
    public void testAddAndGetAndRemoveDipendente(){
        root.addDipendente(dipendente1);
        root.addDipendente(dipendente2);
        assertTrue(root.getDipendenti().contains(dipendente1));
        assertTrue(root.getDipendenti().contains(dipendente2));
        root.removeDipendente(dipendente1);
        root.removeDipendente(dipendente2);
        assertFalse(root.getDipendenti().contains(dipendente1));
        assertFalse(root.getDipendenti().contains(dipendente2));
    }
    @Test
    public void testAddAndRemoveRole(){
        root.addRole(role1);
        root.addRole(role2);
        assertTrue(root.getRoles().contains(role1));
        assertTrue(root.getRoles().contains(role2));

        root.removeRole(role1);
        root.removeRole(role2);
        assertFalse(root.getRoles().contains(role1));
        assertFalse(root.getRoles().contains(role2));

    }
    @Test
    public void testExtendRoleVisitor(){

        root.addRole(role2);
        root.addFiglio(figlio1);
        new ExtendRoleVisitor().visit(root);
        figlio1.addFiglio(figlio3);
        new ExtendRoleVisitor().visit(root);
        assertTrue(figlio3.getRoles().contains(role2));

    }

    @Test
    public void testPositionerVisitor(){
        setup();

        ComponenteVisitor visitor = new ComponenteVisitor(panel);
        //solo nodo radice

        root.accept(visitor);
        assertEquals(HORIZONTAL_OFFSET, gRoot.getX());
        assertEquals(VERTICAL_OFFSET, gRoot.getY());

        //radice + un figlio (sono posizionati in colonna)
        visitor = new ComponenteVisitor(panel);//resetto lo stato del visitor
        root.addFiglio(figlio1);
        root.accept(visitor);

        assertEquals(HORIZONTAL_OFFSET, gRoot.getX());
        assertEquals(HORIZONTAL_OFFSET, gfiglio1.getX());

        assertEquals(VERTICAL_OFFSET, gRoot.getY());
        assertEquals(VERTICAL_OFFSET+(VERTICAL_SPACE+HEIGHT), gfiglio1.getY());

        //radice + due figli, la radice è posizionata in mezzo ai due figli lungo l'asse delle x
        visitor = new ComponenteVisitor(panel);
        root.addFiglio(figlio3);
        root.accept(visitor);

        assertEquals(HORIZONTAL_OFFSET + (HORIZONTAL_SPACE+WIDTH)/2, gRoot.getX());
        assertEquals(HORIZONTAL_OFFSET, gfiglio1.getX());
        assertEquals(HORIZONTAL_OFFSET+(HORIZONTAL_SPACE+WIDTH), gfiglio3.getX());

        assertEquals(VERTICAL_OFFSET, gRoot.getY());
        assertEquals(VERTICAL_OFFSET + (VERTICAL_SPACE+HEIGHT), gfiglio1.getY());
        assertEquals(VERTICAL_OFFSET + (VERTICAL_SPACE+HEIGHT), gfiglio3.getY());
    }
    private void setup(){


        figlio1.setGraphicUnit(gfiglio1);
        figlio2.setGraphicUnit(gfiglio2);
        figlio3.setGraphicUnit(gfiglio3);

    }


}