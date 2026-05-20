package SingleResponceEntry;


import java.util.ArrayList;
import java.util.List;

class Order {

    private int price;
    private int itemId;
    private String name;

    public Order(int price, int itemId, String name) {
        this.price = price;
        this.itemId = itemId;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemId() {
        return this.itemId;
    }
}

class User {

    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
}

public class ProblematicCode {

    public void processOrder(
            int price, int itemId, String productName
    ) {

        System.out.println(
                "Order has been done for product: "
                        + productName
                        + ", price: "
                        + price
                        + ", itemId: "
                        + itemId
        );
    }

    public int calculateTotalSum(List<Order> orderList) {

        return orderList.stream()
                .mapToInt(Order::getPrice)
                .sum();
    }

    public void sendEmailNotification() {

        System.out.println(
                "After Order processing email send successfully"
        );
    }

    public static void main(String[] args) {

        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(100, 1, "Laptop"));
        orderList.add(new Order(200, 2, "Mouse"));
        orderList.add(new Order(300, 3, "Keyboard"));

        ProblematicCode problematicCode =
                new ProblematicCode();

        for (Order order : orderList) {

            problematicCode.processOrder(
                    order.getPrice(),
                    order.getItemId(),
                    order.getName()
            );
        }

        int total =
                problematicCode.calculateTotalSum(orderList);

        System.out.println(
                "Total Order Price: " + total
        );

        problematicCode.sendEmailNotification();
    }
}