package controllers;

import models.AccountHolder;
import models.AccountsDB;
import models.BankAccount;
import models.HistoryOfOperationsDB;
import views.MessageWindow;

import java.util.ArrayList;

public class HolderController {

    public HolderController(AccountHolder holder) {
    }

    public static void moneyReceipt(Long amount,  String accountNumber){
        BankAccount account = AccountsDB.findAccount(accountNumber);

        account.moneyReceipt(amount);
        HistoryOfOperationsDB.addRecordToHistoryReceipt(account.getAccountNumber(),amount);
        AccountsDB.balanceUpdate(account);
    }
    public static void moneyWithdrawal(Long amount, String accountNumber){
        BankAccount account = AccountsDB.findAccount(accountNumber);

        if(account.getBalanceOnAccount()>=amount) {
            account.moneyWithdrawal(amount);
            HistoryOfOperationsDB.addRecordToHistoryWithdrawal(account.getAccountNumber(),amount);
            AccountsDB.balanceUpdate(account);
        }
        else {
            new MessageWindow("Недостаточно средств на счёте");
        }
    }
    public static void transferMoneyBetweenAccounts( Long amount,  String accountNumFirst, String accountNumSecond){
        BankAccount accountFirst = AccountsDB.findAccount(accountNumFirst);
        BankAccount accountSecond = AccountsDB.findAccount(accountNumSecond);

        if(accountFirst.getBalanceOnAccount()>=amount){
            accountFirst.moneyWithdrawal(amount);
            accountSecond.moneyReceipt(amount);
            HistoryOfOperationsDB.addRecordToHistoryMoneyTransfer(accountFirst.getAccountNumber(), accountSecond, amount);
            HistoryOfOperationsDB.addRecordToHistoryReceipt(accountFirst.getAccountNumber(),amount);
            AccountsDB.balanceUpdate(accountFirst);
            AccountsDB.balanceUpdate(accountSecond);
        }
        else {
            new MessageWindow("Недостаточно средств на счёте");
        }
    }
    public static void openAccount(AccountHolder holder){
        BankAccount bankAccount = new BankAccount((holder.getName()+" "+holder.getSurname()+" "+holder.getPatronymic()));
        (holder.bankAccounts).add(bankAccount);
        AccountsDB.addAccount(bankAccount);

    }
    public static void closeAccount(AccountHolder holder, String accountNumber){
        BankAccount account = AccountsDB.findAccount(accountNumber);
        if(account.getBalanceOnAccount()!=0){
            new MessageWindow("ПЕРЕВЕДИТЕ ДЕНЬГИ С ЗАКРЫВАЕМОГО СЧЁТА!!");
            return;
        }

        AccountsDB.removeAccount(account);
        (holder.bankAccounts).remove(account);
    }
    public static String getHistoryOfOperations(String accountNumber){
        return (HistoryOfOperationsDB.toString(accountNumber));
    }
    public static String[] getListOfAccountsNumbers(String holder){
        return AccountsDB.getListOfAccountsNumbers(holder);
    }
    public static String getInformationOnAllAccounts(AccountHolder holder){
        String informationOnAllAccounts = "";
        ArrayList<BankAccount> bankAccounts= AccountsDB.getListOfAccounts(holder.getName()+" "+holder.getSurname()
                +" "+holder.getPatronymic());
        System.out.println(bankAccounts);
        for (BankAccount account:bankAccounts){
            informationOnAllAccounts += (account.getAccountNumber() + "                           Баланс: "+ account.getBalanceOnAccount() +"\n");
        }
        return informationOnAllAccounts;
    }
    public static int getBalanceOnAllAccounts(String accountNumber){
        return AccountsDB.getBalanceOnAllAccounts(accountNumber);
    }
    public static long getBalance(String accountNumber){
        return AccountsDB.getBalance(accountNumber);
    }

}
