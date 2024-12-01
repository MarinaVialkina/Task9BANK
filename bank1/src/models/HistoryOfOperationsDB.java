package models;

import java.io.Serializable;
import java.sql.*;
import java.time.format.DateTimeFormatter;

import static models.ConnectionData.URL;
import static models.ConnectionData.USER;
import static models.ConnectionData.PASSWORD;

public class HistoryOfOperationsDB implements Serializable {
    private static final String INSERT_HOLDERS_SQL = "INSERT INTO HISTORYOPERATIONS VALUES ('%s', '%s', '%s');";



    public static void addRecordToHistoryReceipt(String accountNumber, Long amount){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(INSERT_HOLDERS_SQL, accountNumber, ("   Поступление средств: "+amount+"руб."),
                    (java.time.LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void   addRecordToHistoryWithdrawal(String accountNumber, Long amount){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(INSERT_HOLDERS_SQL, accountNumber, ("   Снятие средств: "+amount+"руб."),
                    (java.time.LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void   addRecordToHistoryMoneyTransfer(String accountNumber, BankAccount accountRecipient, Long amount){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(INSERT_HOLDERS_SQL, accountNumber, ("   Перевод средств: "+amount+"руб. на счёт "
                    +accountRecipient.accountNumber), (java.time.LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    public static String toString(String accountNumber) {
        String result = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from HISTORYOPERATIONS WHERE accountNumber="+accountNumber);
            while (resultSet.next()){
                result += (resultSet.getString("operation")+"     "+(resultSet.getString("timeDate") +"_"));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
