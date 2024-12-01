package views;

import controllers.MainController;
import models.AccountHolder;

import javax.swing.*;
import java.awt.*;

public class TwoSectionForm extends JFrame {
    private JTextField fieldPassport1;
    private JTextField fieldName;
    private JTextField fieldSurname;
    private JTextField fieldPatronymic;
    private JTextField fieldPassport2;


    public TwoSectionForm() {
        setTitle("Банковская система");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel titleLabelPK = new JLabel("Поиск клиента", JLabel.LEFT);

        JLabel label = new JLabel("Номер и серия паспорта: ");
        fieldPassport1 = new JTextField(20);
        JPanel fieldPanel = new JPanel(new GridLayout(0, 2));
        fieldPanel.add(label);
        fieldPanel.add(fieldPassport1);

        JButton searchButton = new JButton("Поиск");



        JLabel titleLabelRK = new JLabel("Регистрация клиента", JLabel.CENTER);

        JLabel labelName = new JLabel("Имя: ");
        fieldName = new JTextField(20);
        JLabel labelSurname = new JLabel("Фамилия: ");
        fieldSurname = new JTextField(20);
        JLabel labelPatronymic = new JLabel("Отчество: ");
        fieldPatronymic = new JTextField(20);
        JLabel labelPassport = new JLabel("Номер и серия паспорта: ");
        fieldPassport2 = new JTextField(20);
        JPanel fieldPanel1 = new JPanel(new GridLayout(0, 2));
        fieldPanel1.add(labelName);
        fieldPanel1.add(fieldName);
        fieldPanel1.add(labelSurname);
        fieldPanel1.add(fieldSurname);
        fieldPanel1.add(labelPatronymic);
        fieldPanel1.add(fieldPatronymic);
        fieldPanel1.add(labelPassport);
        fieldPanel1.add(fieldPassport2);

        JButton registerButton = new JButton("Зарегистрировать");



        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabelPK)
                    .addComponent(titleLabelRK))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fieldPanel)
                        .addComponent(fieldPanel1)
                        .addComponent(searchButton)
                        .addComponent(registerButton)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(titleLabelPK)
                .addComponent(fieldPanel)
                .addComponent(searchButton)
                .addComponent(titleLabelRK)
                .addComponent(fieldPanel1)
                .addComponent(registerButton));

        searchButton.addActionListener(e -> {
            AccountHolder holder = MainController.findHolder(Integer.parseInt(fieldPassport1.getText()));
            if (holder!=null)
                new ClientAccount(holder);


        });
        registerButton.addActionListener(e -> {
            MainController.create(fieldName.getText(), fieldSurname.getText(), fieldPatronymic.getText(),
                    Integer.parseInt(fieldPassport2.getText()));

        });



        add(panel);

        pack();
        setVisible(true);
    }





    public static void main(String[] args) {
        new TwoSectionForm();
    }


}