package models;

import java.util.HashMap;
import java.util.Map;

public class HoldersDB {
    private static Map<Integer,AccountHolder> dataBaseHolders = new HashMap<>();

    public static void addHolder(AccountHolder holder){
        if (dataBaseHolders.containsKey(holder.getPassportNumberAndSeries())){
            System.out.println("Клиент с такими данными уже есть в базе");
            return;
        }
        dataBaseHolders.put(holder.getPassportNumberAndSeries(), holder);
    }
    public static AccountHolder findHolder(int passportNumberAndSeries){
        if (dataBaseHolders.containsKey(passportNumberAndSeries)){
            return dataBaseHolders.get(passportNumberAndSeries);
        }
        return null;
    }


}
