import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
     JFrame frame = new JFrame();
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(800, 600);
     frame.setVisible(true);
     JPanel panel = new JPanel();
     frame.add(panel);
     panel.setLayout(null);
     JButton button1 = new JButton("Button1");
     panel.add(button1);
     JButton button2 = new JButton("Button2");
     panel.add(button2);
     JButton button3 = new JButton("Button3");
     panel.add(button3);

    }
}