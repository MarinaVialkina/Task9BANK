package models;

import java.util.ArrayList;

public class HistoryOfOperations {

    private ArrayList<String> history;

    public HistoryOfOperations() {
        this.history = new ArrayList<>();
    }


    public void addRecordToHistoryReceipt(Long amount){
        this.history.add(java.time.LocalDateTime.now()+"  Поступление средств:"+amount+"руб.");
    }
    public void   addRecordToHistoryWithdrawal(Long amount){
        this.history.add(java.time.LocalDateTime.now()+"  Снятие средств:"+amount+"руб.");
    }
    public void   addRecordToHistoryMoneyTransfer(BankAccount accountRecipient,Long amount){
        this.history.add(java.time.LocalDateTime.now()+"  Перевод средств:"+amount+"руб. на счёт"+accountRecipient.accountNumber);
    }




    @Override
    public String toString() {
        String historyForPrint = "";
        for(String str:history){
            historyForPrint+=str;
            historyForPrint+="\n";
        }

        return historyForPrint;
    }
}
