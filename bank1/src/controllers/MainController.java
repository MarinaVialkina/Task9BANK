package controllers;

import models.AccountHolder;
import models.HoldersDB;
import views.MessageWindow;


public class MainController {
    public static void create(String name, String surname, String patronymic, int passportNumberAndSeries){
        if(((""+passportNumberAndSeries).length() != 10) || name.isEmpty() || surname.isEmpty()){
            new MessageWindow("Неверно введены данные");
            return;
        }
        name = (name.substring(0, 1).toUpperCase()+ name.substring(1, name.length()).toLowerCase()).trim();
        surname = (surname.substring(0, 1).toUpperCase()+ surname.substring(1, surname.length()).toLowerCase()).trim();
        patronymic = (patronymic.substring(0, 1).toUpperCase()+ patronymic.substring(1, patronymic.length()).toLowerCase()).trim();

        AccountHolder holder = new AccountHolder(name, surname, patronymic, passportNumberAndSeries);
        HoldersDB.addHolder(holder);
        new MessageWindow("Клиент зарегистрирован ");

    }
    public static AccountHolder findHolder(int passportNumberAndSeries){
        if (HoldersDB.findHolder(passportNumberAndSeries) == null){
            new MessageWindow("Клиента с такими паспортными данными не существует");
            return null;
        }
        return HoldersDB.findHolder(passportNumberAndSeries);

    }

}
