package models;

import java.io.Serializable;

public class BankAccount implements Serializable {

    public String accountNumber;
    private Long balanceOnAccount;


    private String holder;

    public BankAccount(String holder) {
        this.accountNumber = accountNumberGeneration();
        this.balanceOnAccount = 0L;
        this.holder = holder;
    }

    public BankAccount(String accountNumber, Long balanceOnAccount, String holder) {
        this.accountNumber = accountNumber;
        this.balanceOnAccount = balanceOnAccount;
        this.holder = holder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getBalanceOnAccount() {
        return balanceOnAccount;
    }
    public String getHolder() {
        return holder;
    }

    public void moneyReceipt(Long amount){
        this.balanceOnAccount+=amount;
    }
    public void moneyWithdrawal(Long amount){
        this.balanceOnAccount-=amount;
    }
    private String accountNumberGeneration(){
        String accountNumber = "";
        for (int i = 0; i < 20; i++) {
            accountNumber +=(int)(Math.random()*(10));
        }

        if (AccountsDB.findAccount(accountNumber)!=null) {
            accountNumberGeneration();
        }
        return accountNumber;

    }
}
