import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

void main() {

    int N = 1;

    Scanner scanner = new Scanner(System.in);
    System.out.print("Здравствуйте, выберете действие:\n");

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
