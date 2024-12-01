package models;


import java.sql.*;
import java.util.ArrayList;

import static models.ConnectionData.URL;
import static models.ConnectionData.USER;
import static models.ConnectionData.PASSWORD;

public class AccountsDB {
    private static final String INSERT_ACCOUNTS_SQL = "INSERT INTO ACCOUNTSDB VALUES ('%s', %s, '%s');";
    private static final String REMOVE_ACCOUNTS_SQL = "DELETE FROM ACCOUNTSDB WHERE accountNumber='%s';";
    private static final String UPDATE_ACCOUNTS_SQL = "UPDATE ACCOUNTSDB SET balance=%s  WHERE accountNumber=%s;";

    public static void addAccount(BankAccount account){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(INSERT_ACCOUNTS_SQL, account.accountNumber, account.getBalanceOnAccount(), account.getHolder());
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeAccount(BankAccount account){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement()) {
            String insertSql = String.format(REMOVE_ACCOUNTS_SQL, account.accountNumber);
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static BankAccount findAccount(String accountNumber){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ACCOUNTSDB");
            while (resultSet.next()){
                if (resultSet.getString("accountNumber").equals(accountNumber)){
                    return new BankAccount(accountNumber, (long) resultSet.getInt("balance"),
                            resultSet.getString("holder"));

                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public static void balanceUpdate(BankAccount account){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(UPDATE_ACCOUNTS_SQL, account.getBalanceOnAccount(),account.accountNumber);
            statement.execute(insertSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Long getBalance(String accountNumber){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ACCOUNTSDB");
            while (resultSet.next()){
                if (resultSet.getString("accountNumber").equals(accountNumber)){
                    return (long) resultSet.getInt("balance");
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public static int getBalanceOnAllAccounts(String holder){
        int balanceOnAllAccounts = 0;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ACCOUNTSDB");
            while (resultSet.next()){
                if( resultSet.getString("holder").equals(holder))
                    balanceOnAllAccounts+=resultSet.getInt("balance");

            }
            return balanceOnAllAccounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public static String[] getListOfAccountsNumbers(String holder){
        String accountsNumbers = "";
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ACCOUNTSDB");
            while (resultSet.next()){
                if( resultSet.getString("holder").equals(holder))
                    accountsNumbers+=(resultSet.getString("accountNumber")+".");


            }
            return accountsNumbers.split("[.]");
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    public static ArrayList<BankAccount> getListOfAccounts(String holder){
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from ACCOUNTSDB");
            while (resultSet.next()){
                if( resultSet.getString("holder").equals(holder))
                    bankAccounts.add(new BankAccount(resultSet.getString("accountNumber"),
                            (long) resultSet.getInt("balance"),resultSet.getString("holder")));



            }
            return bankAccounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

}
