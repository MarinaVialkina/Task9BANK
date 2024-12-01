package views;

import javax.swing.*;
import java.awt.*;

public class MessageWindow extends JFrame {
    public MessageWindow(String messageError) {
        JDialog.setDefaultLookAndFeelDecorated(true);

        JDialog dialog = new JDialog(this, "Message", true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialog.setSize(500, 300);

        Container container = dialog.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Message ");
        title.setAlignmentX(Component.TOP_ALIGNMENT);
        container.add(title);

        JLabel labelAccNum = new JLabel(messageError);
        labelAccNum.setAlignmentX(Component.RIGHT_ALIGNMENT);
        container.add(labelAccNum);



        dialog.pack();
        dialog.setVisible(true);
    }



}
