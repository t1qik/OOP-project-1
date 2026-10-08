import java.util.ArrayList;
import java.util.List;

public class Order {

    int numb; //Номер заказа
    List<Product> productList = new ArrayList<>();//Список заказанных продуктов
    float sumPrice; //Итоговая сумма заказа
    float sumWeight;
    String deliveryType;
    String Status;
    Courier courier;
    float deliveryPrice = 0;
    User user;
    //Конструктор класса заказа
    Order(int N){
        if(N > 0) {
            numb = N;
        }
    }
    //метод заполнения продуктов, которые заказали
    void addProdList(List<Integer> choiceOfProd, List<Product> defProdList){
        List<Product> temp = new ArrayList<>();

        for(int i = 0; i < choiceOfProd.size(); i++){
            Product src = defProdList.get(choiceOfProd.get(i) - 1);
            Product copy = new Product(src.name, src.price, src.weight, src.numbOfProduct);
            temp.add(copy);
        }

        for(int j = 0; j < temp.size(); j++){
            for(int i = j + 1; i < temp.size(); i++){
                if(temp.get(j).name.equals(temp.get(i).name)){
                    temp.get(j).numbOfProduct += temp.get(i).numbOfProduct;
                    temp.remove(i);
                    i--;
                }
            }
        }

        productList = temp;
    }
    //метод расчета итоговой суммы
    void calculateSum(){
        float SUM = 0;
        float WEIGHT = 0;
        for(int i = 0; i < productList.size(); i++){
            SUM += productList.get(i).price * productList.get(i).numbOfProduct;
            WEIGHT += productList.get(i).weight * productList.get(i).numbOfProduct;
        }
        sumPrice = SUM;
        sumWeight = WEIGHT;
    }
    //метод вывода списка заказанных продуктов
    void outProdList(){
        for(int i = 0; i < productList.size(); i++){
            System.out.println((i+1)+") " + productList.get(i).name);
            System.out.println("    Цена: " + productList.get(i).price + " руб");
            System.out.println("    Вес: " + productList.get(i).weight + " кг");
            System.out.println("    Кол-во шт: " + productList.get(i).numbOfProduct);

        }
    }
    void outOrder(){
        System.out.println("Заказ №" + numb + " Статус: " + Status +"\nПользователь: " + user.name + ": " + user.number);
        if (deliveryType.equals("Доставка")  || deliveryType.equals("Экспресс доставка")){
            System.out.println("Способ доставки: " + deliveryType + " Курьер: " + courier.name);
        }
        else System.out.println("Способ доставки: " + deliveryType);
        if (deliveryType.equals("Доставка")  || deliveryType.equals("Экспресс доставка")){
            System.out.println("Срок доставки: " + courier.time + " мин.");
        }
        System.out.println("Итоговый вес: " + sumWeight + " Итоговая цена: " + sumPrice);
        System.out.println("Продукты:");
        for(int i = 0; i < productList.size(); i++) {
            System.out.println((i+1) + ")" + productList.get(i).name + ",шт - " + productList.get(i).numbOfProduct);
        }
        System.out.println("\n");
    }
}