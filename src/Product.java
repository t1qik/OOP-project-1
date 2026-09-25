public class Product {
    String name; //Наименование продукта
    float price; //Цена продукта за единицу веса/упаковки
    float weight; //Вес продукта
    int numbOfProduct; //Кол-во штук(упаковок)

    Product(String n, float p, float w, int m){
        name = n;
        price = p;
        weight = w;
        numbOfProduct = m;
    }

    String getName(){
        return name;
    }
    float getPrice(){
        return price;
    }
    float getWeight(){
        return weight;
    }
    int getNumb(){
        return numbOfProduct;
    }
}
