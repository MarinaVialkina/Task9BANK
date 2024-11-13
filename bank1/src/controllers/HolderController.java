package controllers;

import models.AccountHolder;
import models.AccountsDB;
import models.BankAccount;
import models.HistoryOfOperationsDB;

import java.util.ArrayList;
import java.util.Scanner;

public class HolderController {
    private AccountHolder holder;

    public HolderController(AccountHolder holder) {
        this.holder = holder;
    }

    public void moneyReceipt(Long amount){
        BankAccount account = selectAccount();
        account.moneyReceipt(amount);
        HistoryOfOperationsDB.addRecordToHistoryReceipt(account.getAccountNumber(),amount);
        AccountsDB.balanceUpdate(account);
    }
    public void moneyWithdrawal(Long amount){
        BankAccount account = selectAccount();
        if(account.getBalanceOnAccount()>=amount) {
            account.moneyWithdrawal(amount);
            HistoryOfOperationsDB.addRecordToHistoryWithdrawal(account.getAccountNumber(),amount);
            AccountsDB.balanceUpdate(account);
        }
        else {
            System.out.println("Недостаточно средств");
        }
    }
    public void transferMoneyBetweenAccounts( Long amount){
        BankAccount accountFirst = selectAccount();
        BankAccount accountSecond = selectAccount();
        if(accountFirst.getBalanceOnAccount()>=amount){
            accountFirst.moneyWithdrawal(amount);
            accountSecond.moneyReceipt(amount);
            HistoryOfOperationsDB.addRecordToHistoryMoneyTransfer(accountFirst.getAccountNumber(), accountSecond, amount);
            HistoryOfOperationsDB.addRecordToHistoryReceipt(accountFirst.getAccountNumber(),amount);
            AccountsDB.balanceUpdate(accountFirst);
            AccountsDB.balanceUpdate(accountSecond);
        }
        else {
            System.out.println("Недостаточно средств");
        }
    }
    public void openAccount(){
        BankAccount bankAccount = new BankAccount((holder.getName()+" "+holder.getSurname()+" "+holder.getPatronymic()));
        (holder.bankAccounts).add(bankAccount);
        AccountsDB.addAccount(bankAccount);

    }
    public void closeAccount(){
        BankAccount account = selectAccount();
        while (account.getBalanceOnAccount()!=0){
            System.out.println("    Перевод денег с закрываемого счёта");
            transferMoneyBetweenAccounts(account.getBalanceOnAccount());
        }

        AccountsDB.removeAccount(account);
        (holder.bankAccounts).remove(account);
    }
    public String getHistoryOfOperations(){
        String accountNumber = selectAccount().getAccountNumber();
        return (HistoryOfOperationsDB.toString(accountNumber));
    }
    public Long getBalanceOnAccount(){
        BankAccount account = selectAccount();
        return (account.getBalanceOnAccount());
    }
    public String getInformationOnAllAccounts(){
        String informationOnAllAccounts = "";
        ArrayList<BankAccount> bankAccounts= holder.bankAccounts;
        for (BankAccount account:bankAccounts){
            informationOnAllAccounts+=(account.getAccountNumber() + " Баланс: "+ account.getBalanceOnAccount() +"\n");
        }
        return informationOnAllAccounts;
    }
    private BankAccount selectAccount(){
        ArrayList<BankAccount> bankAccounts= holder.bankAccounts;

        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println("          Номер "+i+":"+bankAccounts.get(i).getAccountNumber());
        }
        int accountNumber = -1;
        do{
            System.out.print("          Введите номер счёта: ");
            Scanner scanner = new Scanner(System.in);
            accountNumber = scanner.nextInt();

        }while (  (accountNumber < 0) || (accountNumber>=bankAccounts.size())  );

        return bankAccounts.get(accountNumber);
    }
}
