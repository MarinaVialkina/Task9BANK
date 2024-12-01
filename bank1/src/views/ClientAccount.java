package views;

import controllers.HolderController;
import models.AccountHolder;

import javax.swing.*;
import java.awt.*;



public class ClientAccount extends JFrame {

    private JComboBox<String> accountsComboBox;
    private JTextField textFieldPlus;
    private JTextField textFieldMinus;
    private JTextField textFieldTranslation;
    private JComboBox<String> account1comboBox;
    private JComboBox<String> account2comboBox;



    public ClientAccount(AccountHolder holder) {
        setTitle("Аккаунт клиента");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        JLabel titleLabel = new JLabel(holder.getSurname()+" "+holder.getName()+" "+holder.getPatronymic());
        JLabel subTitleLabel = new JLabel("Номер и серия паспорта: "+holder.getPassportNumberAndSeries());
        add(titleLabel);
        add(subTitleLabel);


        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel account = new JLabel("Счёт: ", SwingConstants.LEFT);
        accountsComboBox = new JComboBox<>(HolderController.getListOfAccountsNumbers(holder.getName()+
                " "+holder.getSurname()+" "+holder.getPatronymic()));
        JButton updateButton = new JButton("Обновить");
        JButton openButton = new JButton("Открыть счёт");
        JButton closeButton = new JButton("Закрыть счёт");
        accountPanel.add(account);
        accountPanel.add(accountsComboBox);
        accountPanel.add(updateButton);
        accountPanel.add(openButton);
        accountPanel.add(closeButton);
        add(accountPanel);


        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Баланс: "+HolderController.getBalance(String.valueOf(accountsComboBox.getSelectedItem())) +
                "          Баланс по всем счетам: "+HolderController.getBalanceOnAllAccounts(holder.getName()+
                " "+holder.getSurname()+" "+holder.getPatronymic()));
        balancePanel.add(label);
        add(balancePanel);


        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        textFieldPlus = new JTextField(10);
        JButton plusButton = new JButton("+");
        textFieldMinus = new JTextField(10);
        JButton minusButton = new JButton("-");
        inputPanel.add(textFieldPlus);
        inputPanel.add(plusButton);
        inputPanel.add(textFieldMinus);
        inputPanel.add(minusButton);
        add(inputPanel);


        JPanel transferBetweenAccPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        account1comboBox = new JComboBox<>(HolderController.getListOfAccountsNumbers(holder.getName()+
                " "+holder.getSurname()+" "+holder.getPatronymic()));
        JButton transferButton = new JButton("--->");
        account2comboBox = new JComboBox<>(HolderController.getListOfAccountsNumbers(holder.getName()+
                " "+holder.getSurname()+" "+holder.getPatronymic()));
        textFieldTranslation = new JTextField(10);
        transferBetweenAccPanel.add(account1comboBox);
        transferBetweenAccPanel.add(transferButton);
        transferBetweenAccPanel.add(account2comboBox);
        transferBetweenAccPanel.add(new JLabel("перевести: "));
        transferBetweenAccPanel.add(textFieldTranslation);
        transferBetweenAccPanel.add(new JLabel("руб"));
        add(transferBetweenAccPanel);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonHistory = new JButton("Получить историю");
        JButton buttonInfo = new JButton("Получить информацию по всем счетам");
        buttonPanel.add(buttonHistory);
        buttonPanel.add(buttonInfo);
        add(buttonPanel);


        openButton.addActionListener(e -> {
            HolderController.openAccount(holder);
            dispose();
            new ClientAccount(holder);


        });
        closeButton.addActionListener(e -> {
            HolderController.closeAccount(holder,String.valueOf(accountsComboBox.getSelectedItem()));
            dispose();
            new ClientAccount(holder);


        });

        updateButton.addActionListener(e -> {

            label.setText("Баланс: "+HolderController.getBalance(String.valueOf(accountsComboBox.getSelectedItem())) +
                    "          Баланс по всем счетам: "+HolderController.getBalanceOnAllAccounts(holder.getName()+" "
                    +holder.getSurname()+" "+holder.getPatronymic()));



        });

        plusButton.addActionListener(e -> {
            HolderController.moneyReceipt(Long.parseLong(textFieldPlus.getText()),  String.valueOf(accountsComboBox.getSelectedItem()));
            updateButton.doClick();


        });
        minusButton.addActionListener(e -> {
            HolderController.moneyWithdrawal(Long.parseLong(textFieldMinus.getText()),  String.valueOf(accountsComboBox.getSelectedItem()));
            updateButton.doClick();
        });
        transferButton.addActionListener(e -> {
            HolderController.transferMoneyBetweenAccounts(Long.parseLong(textFieldTranslation.getText()),
                    (String) account1comboBox.getSelectedItem(), (String) account2comboBox.getSelectedItem());
            updateButton.doClick();
        });
        buttonHistory.addActionListener(e -> {
            new HistoryWindow(String.valueOf(accountsComboBox.getSelectedItem()),
                    HolderController.getHistoryOfOperations(String.valueOf(accountsComboBox.getSelectedItem())));
        });
        buttonInfo.addActionListener(e -> {
            new InfoAccountsWindow(holder,
                    HolderController.getInformationOnAllAccounts(holder));
        });


        pack();
        setVisible(true);
    }


}