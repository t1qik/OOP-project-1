import java.util.ArrayList;
import java.util.List;

public class Order {

    int numb; //Номер заказа
    List<Product> productList = new ArrayList<>();//Список заказанных продуктов
    float sumPrice; //Итоговая сумма заказа
    float sumWeight;
    //Конструктор класса заказа
    Order(int N){
        if(N > 0) {
            numb = N;
        }
    }
    //метод заполнения продуктов, которые заказали
    void addProdList(List<Integer> choiceOfProd, List<Product> defProdList){
        for(int i = 0; i < choiceOfProd.size(); i++){

            Product src = defProdList.get(choiceOfProd.get(i) - 1);
            Product copy = new Product(src.name, src.price, src.weight, src.numbOfProduct);

            productList.add(copy);
        }
        for(int j = productList.size() - 1; j >= 0; j--){
            for(int i = j - 1; i >= 0; i--){
                if(productList.get(j).name.equals(productList.get(i).name)){
                    productList.get(j).numbOfProduct += productList.get(i).numbOfProduct;
                    productList.remove(i);
                }
            }
        }
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
}