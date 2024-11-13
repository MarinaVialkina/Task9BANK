package models;

import java.sql.Array;
import java.util.ArrayList;

public class AccountHolder {
    private final String name;
    private final String surname;
    private final String patronymic;
    private final int passportNumberAndSeries;
    public ArrayList<BankAccount> bankAccounts;

    public AccountHolder(String name, String surname, String patronymic, int passportNumberAndSeries) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportNumberAndSeries = passportNumberAndSeries;

        this.bankAccounts = new ArrayList<>();
        BankAccount bankAccount = new BankAccount(name+" "+surname+" "+patronymic);
        bankAccounts.add(bankAccount);
        AccountsDB.addAccount(bankAccount);
    }
    public AccountHolder(String name, String surname, String patronymic, int passportNumberAndSeries, ArrayList<BankAccount> bankAccounts) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.passportNumberAndSeries = passportNumberAndSeries;
        this.bankAccounts = bankAccounts;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getPassportNumberAndSeries() {
        return passportNumberAndSeries;
    }



}
