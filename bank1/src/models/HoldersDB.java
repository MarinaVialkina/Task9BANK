package models;

import views.MessageWindow;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static models.ConnectionData.URL;
import static models.ConnectionData.USER;
import static models.ConnectionData.PASSWORD;

public class HoldersDB {

    private static final String INSERT_HOLDERS_SQL = "INSERT INTO HOLDERSDB VALUES ('%s', '%s', '%s', %s, '%s');";
    private static final String SELECT_HOLDERS_SQL = "select * from HOLDERSDB";


    public static void addHolder(AccountHolder holder){
        if (findHolder(holder.getPassportNumberAndSeries())!=null) {
            new MessageWindow("Клиент с такими данными уже есть в базе");
            return;
        }
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String insertSql = String.format(INSERT_HOLDERS_SQL, holder.getName(), holder.getSurname(),
                    holder.getPatronymic(), holder.getPassportNumberAndSeries(), Serializer.serialize(holder.bankAccounts));
            statement.execute(insertSql);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static AccountHolder findHolder(int passportNumberAndSeries){
        AccountHolder accountHolder = null;
        ArrayList<BankAccount> bankAccounts = null;
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_HOLDERS_SQL);

            while (resultSet.next()){
                if (resultSet.getInt("passportNumberAndSeries")==passportNumberAndSeries){
                    break;
                }
            }

            bankAccounts = (ArrayList<BankAccount>) Serializer.deserialize(resultSet.getString("bankAccounts"));
            accountHolder = new AccountHolder(resultSet.getString("name"),
                    resultSet.getString("surname"),resultSet.getString("patronymic"),
                    resultSet.getInt("passportNumberAndSeries"), bankAccounts);

        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            return accountHolder;
        }

    }


}
