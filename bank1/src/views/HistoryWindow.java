package views;

import javax.swing.*;
import java.awt.*;


public class HistoryWindow extends JFrame {
    public HistoryWindow(String accountNumber, String history) {
        JDialog.setDefaultLookAndFeelDecorated(true);

        JDialog dialog = new JDialog(this, "История операций", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 300);

        Container container = dialog.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("История операций ");
        title.setAlignmentX(Component.TOP_ALIGNMENT);
        container.add(title);

        JLabel labelAccNum = new JLabel("Номер счёта: "+accountNumber);
        labelAccNum.setAlignmentX(Component.RIGHT_ALIGNMENT);
        container.add(labelAccNum);


        String[] strings = history.split("[_]");
        for (String str: strings){
            JLabel label = new JLabel(str);
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            container.add(label);
        }

        dialog.pack();
        dialog.setVisible(true);
    }



}
