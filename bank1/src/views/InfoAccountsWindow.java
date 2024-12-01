package views;

import models.AccountHolder;

import javax.swing.*;
import java.awt.*;

public class InfoAccountsWindow extends JFrame {
    public InfoAccountsWindow(AccountHolder holder, String info) {
        JDialog.setDefaultLookAndFeelDecorated(true);

        JDialog dialog = new JDialog(this, "Информация по всем счетам", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 300);

        Container container = dialog.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Информация по всем счетам");
        title.setAlignmentX(Component.TOP_ALIGNMENT);
        container.add(title);

        JLabel labelAccNum = new JLabel("Клиент: "+holder.getSurname()+" "+holder.getName()+" "+holder.getPatronymic());
        labelAccNum.setAlignmentX(Component.RIGHT_ALIGNMENT);
        container.add(labelAccNum);


        String[] strings = info.split("\n");
        for (String str: strings){
            JLabel label = new JLabel(str);
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            container.add(label);
        }

        dialog.pack();
        dialog.setVisible(true);
    }



}
