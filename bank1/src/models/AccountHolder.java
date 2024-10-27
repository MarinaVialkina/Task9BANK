package models;

import java.util.ArrayList;

public class AccountHolder {
    private String name;
    private String surname;
    private String patronymic;
    private int passportNumberAndSeries;
    public ArrayList<BankAccount> bankAccounts;

    public AccountHolder(String name, String surname, String patronymic, int passportNumberAndSeries) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportNumberAndSeries = passportNumberAndSeries;
        this.bankAccounts = new ArrayList<>();
        bankAccounts.add(new BankAccount());
    }

    public int getPassportNumberAndSeries() {
        return passportNumberAndSeries;
    }



}
