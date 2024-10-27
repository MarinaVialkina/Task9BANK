package controllers;

import models.AccountHolder;
import models.HoldersDB;


public class MainController {
    public void create(String name, String surname, String patronymic, int passportNumberAndSeries){
        if(((""+passportNumberAndSeries).length() != 10) || name.isEmpty() || surname.isEmpty()){
            System.out.println("Неверно введены данные");
            return;
        }
        name = (name.substring(0, 1).toUpperCase()+ name.substring(1, name.length()).toLowerCase()).trim();
        surname = (surname.substring(0, 1).toUpperCase()+ surname.substring(1, surname.length()).toLowerCase()).trim();
        patronymic = (patronymic.substring(0, 1).toUpperCase()+ patronymic.substring(1, patronymic.length()).toLowerCase()).trim();

        AccountHolder holder = new AccountHolder(name, surname, patronymic, passportNumberAndSeries);
        HoldersDB.addHolder(holder);

    }
    public HolderController findHolder(int passportNumberAndSeries){
        if (HoldersDB.findHolder(passportNumberAndSeries) == null){
            System.out.println("Клиента с такими паспортными данными не существует");
            return null;
        }
        return new HolderController(HoldersDB.findHolder(passportNumberAndSeries));

    }

}
