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

    Scanner scanner = new Scanner(System.in);

    System.out.print("Здравствуйте, выберите действие:\n");
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

                System.out.println(("Итоговая цена:" + order.sumPrice + " Итоговый вес:" + order.sumWeight));

                System.out.println("Заказ номер: " + N + " успешно оформлен");
                //добавление зказа в список
                listOrders.add(order);
                N++;
                break;
            case 2:
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
//Оформленно чтение из файла
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
            Courier newcourier = new Courier(parts[0], rating, weight);

            defListCourier.add(newcourier);
        }
    } catch (IOException e){
        e.printStackTrace();
    }
    return defListCourier;
}