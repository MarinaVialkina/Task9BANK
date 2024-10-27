package models;

import java.util.HashMap;
import java.util.Map;

public class AccountsDB {
    private static Map<String,BankAccount> dataBaseAccounts = new HashMap<>();


    public static void addAccount(BankAccount account){
        dataBaseAccounts.put(account.getAccountNumber(), account);
    }
    public static void removeAccount(BankAccount account){
        dataBaseAccounts.remove(account.getAccountNumber());
    }
    public static BankAccount findAccount(String accountNumber){
        if (dataBaseAccounts.containsKey(accountNumber)){
            return dataBaseAccounts.get(accountNumber);
        }
        return null;
    }
}
