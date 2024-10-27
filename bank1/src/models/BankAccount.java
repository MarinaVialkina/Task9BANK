package models;

public class BankAccount {

    public String accountNumber;
    private Long balanceOnAccount;
    public HistoryOfOperations historyOfOperations;

    public BankAccount() {
        this.accountNumber = accountNumberGeneration();
        this.balanceOnAccount = 0L;
        this.historyOfOperations = new HistoryOfOperations();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Long getBalanceOnAccount() {
        return balanceOnAccount;
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
