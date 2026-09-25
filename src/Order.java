import java.util.ArrayList;
import java.util.List;

public class Order {

    int numb; //Номер заказа
    List<Product> productList = new ArrayList<>();

    //Конструктор класса заказа
    Order(int N){
        if(N > 0) {
            numb = N;
        }
    }
    //метод создания заказа
    void addOrder(){

    }
}