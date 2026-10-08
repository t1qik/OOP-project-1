import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

void main() {

    int N = 1;

    List<Order> listOrders = new ArrayList<>();
    List<Product> defListProd = fillProdList();
    List<Courier> defListCourier = fillCourierList();

    List<Courier> expressCourier = new ArrayList<>();
    for(int i = 0; i < defListCourier.size(); i++){
        if(defListCourier.get(i).time <= 25){
            expressCourier.add(defListCourier.get(i));
        }
    }

    Scanner scanner = new Scanner(System.in);

    System.out.println("Здравствуйте, введите свое имя и номер телефона");
    User user = new User(scanner.next(),scanner.next());

    System.out.print("Выберите действие:\n");
    while(true) {
        System.out.print("1)Создать новый заказ и добавить в него одну или несколько позиций;\n" +
                "2)Показать список существующих заказов;\n" +
                "3)Выбрать для заказа способ доставки и показать рассчитанные стоимость и срок;\n" +
                "4)Изменить статус заказа и показать его текущую информацию;\n" +
                "5)Завершить работу\n");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Order order = new Order(N);
                order.user = user;

                System.out.println("Выберите номер продукта/продуктов из списка, который хотите заказать:");
                for(int i = 0; i < defListProd.size(); i++){
                    System.out.println((i+1)+") " + defListProd.get(i).name);
                    System.out.println("    Цена: " + defListProd.get(i).price + " руб");
                    System.out.println("    Вес: " + defListProd.get(i).weight + " кг");
                    System.out.println("    Кол-во шт: " + defListProd.get(i).numbOfProduct);
                }

                List<Integer> choiceOfProd = new ArrayList<>();

                while(scanner.hasNextInt()){
                    int v = scanner.nextInt();
                    if ( v == 0) break;
                    choiceOfProd.add(v);
                }

                for(int i = choiceOfProd.size() - 1; i >= 0 ; i--)
                {
                    if((choiceOfProd.get(i) <= 0)||(choiceOfProd.get(i) > defListProd.size())){
                        System.out.println("Ошибка! Продукта под номером:" + choiceOfProd.get(i) + " не существует");
                        choiceOfProd.remove(i);
                    }
                }

                //Запись списка продуктов в заказ
                order.addProdList(choiceOfProd,defListProd);
                order.calculateSum();

                System.out.printf("Цена заказа: %.2f руб, Итоговый вес: %.2f кг%n", order.sumPrice, order.sumWeight);

                System.out.println("\nВыберите способ доставки:\n1)Доставка\n2)Экспресс доставка\n3)Самовывоз");
                int choiceDelivery = scanner.nextInt();
                switch (choiceDelivery){
                    case 1:
                        order.deliveryType = "Доставка";
                        order.deliveryPrice += 200;
                        outCourierList(defListCourier, scanner, order);
                        break;
                    case 2:
                        order.deliveryType = "Экспресс досавка";
                        order.deliveryPrice += 400;
                        outCourierList(expressCourier, scanner, order);
                        break;
                    case 3:
                        order.deliveryType = "Самовывоз";
                        break;
                }

                System.out.println("Заказ номер: " + N + " успешно оформлен\nК оплате:");
                System.out.printf("%.2f%n", order.sumPrice + order.deliveryPrice);
                //добавление зказа в список
                listOrders.add(order);
                N++;
                break;
            case 2:
                for(int i = 0; i < listOrders.size(); i++){
                    listOrders.get(i).outOrder();
                }
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                return;
            default:
                System.out.println("Неправильно введены данные, попробуйте еще раз");
        }
    }
}

//Функция заполнения списка позиций, которые можно выбрать для доставки
//Оформленно чтение из файла списка продуктов для заказа
List<Product> fillProdList(){

    List<Product> defListProd = new ArrayList<>();

    Path path = Paths.get("DataBase1.txt");

    try {

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        int a = lines.size();

        for(int i = 0; i < a; i++){

            String line = lines.get(i);
            String[] parts = line.split("\\s+");

            float price = Float.parseFloat(parts[1]);
            float weight = Float.parseFloat(parts[2]);
            int numbOfProduct = Integer.parseInt(parts[3]);

            Product newproduct = new Product(parts[0], price, weight, numbOfProduct);

            defListProd.add(newproduct);
        }
    } catch (IOException e){
        e.printStackTrace();
    }
    return defListProd;
}
//Оформленно чтение из файла списка доставщиков для заказа
List<Courier> fillCourierList(){

    List<Courier> defListCourier = new ArrayList<>();

    Path path = Paths.get("DataBase2.txt");

    try {

        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        int a = lines.size();

        for(int i = 0; i < a; i++){

            String line = lines.get(i);
            String[] parts = line.split("\\s+");

            float rating = Float.parseFloat(parts[1]);
            float weight = Float.parseFloat(parts[2]);
            int time = Integer.parseInt(parts[3]);
            Courier newcourier = new Courier(parts[0], rating, weight, time);

            defListCourier.add(newcourier);
        }
    } catch (IOException e){
        e.printStackTrace();
    }
    return defListCourier;
}
//Вывод списка курьеров для заказа
void outCourierList(List<Courier> courierList, Scanner scanner, Order order){
    System.out.println("Выберите курьера:");
    for (int i = 0; i < courierList.size(); i++){
        System.out.println((i + 1) + ")Курьер:" + courierList.get(i).name +
                " Рейтинг:" + courierList.get(i).rating +
                " \nМаксимальный перевозимый вес:" + courierList.get(i).weight +
                " Время доставки:" + courierList.get(i).time);
    }
    while(true){
        int idx = scanner.nextInt();
        if(idx < 1 || idx > courierList.size()){
            System.out.println("Курьера с таким номером не существует");
            continue;
        }
        Courier c = courierList.get(idx - 1);
        if(order.sumWeight > c.weight){
            System.out.println("Курьер не может увезти заказ: превышен вес");
        } else {
            order.courier = c;
            System.out.println("Курьер удачно добавлен");
            break;
        }
    }
}