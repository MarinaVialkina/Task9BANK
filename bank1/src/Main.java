import controllers.HolderController;
import controllers.MainController;

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.create("Иванов","Иван", "Иванович", 1920564736);
        mainController.create("Смирнов","Олег", "Васильевич", 1920564737);
        mainController.create("Кузнецова","Мария", "Ивановна", 1920564738);
        mainController.create("Попова","Ирина", "Егоровна", 1920564739);

        HolderController holderController = mainController.findHolder(1920564736);

        System.out.println("Поcтупление  ");
        holderController.moneyReceipt(2000L);

        System.out.println("Снятие  ");
        holderController.moneyWithdrawal(1000L);

        System.out.println("Получение баланса");
        System.out.println("Баланс " + holderController.getBalanceOnAccount()+ " RUB");

        System.out.println("ОТКРЫТИЕ СЧЁТА");
        holderController.openAccount();

        System.out.println("Получение информации по всем счетам");
        System.out.println(holderController.getInformationOnAllAccounts());

        System.out.println("Перевод с одного счёта на другой");
        holderController.transferMoneyBetweenAccounts(100L);

        System.out.println("ЗАКРЫТИЕ СЧЁТА");
        holderController.closeAccount();

        System.out.println("История");
        System.out.println(holderController.getHistoryOfOperations());
    }
}
