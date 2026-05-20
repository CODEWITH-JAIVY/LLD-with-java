package SingleResponceEntry;

import java.util.ArrayList;
import java.util.List;

class Orders  {

    private int price;
    private int itemId;
    private String name;

    public Orders (int price, int itemId, String name) {
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

 class processOrder {
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
 }

  class calculateTotalSum {
      public int calculateTotalSum(List<Order> orderList) {

          return orderList.stream()
                  .mapToInt(Order::getPrice)
                  .sum();
      }
  }

  class sendEmailNotification {
      public void sendEmailNotification(String message ) {

          System.out.println(
                  message + " " +

                  "After Order processing email send successfully"

          );
      }
  }

public class SolutionCode {
    public static void main(String[] args) {
        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(100, 1, "Laptop"));
        orderList.add(new Order(200, 2, "Mouse"));
        orderList.add(new Order(300, 3, "Keyboard"));

        processOrder processOrder = new processOrder() ;
        processOrder.processOrder(100, 1 , "Laptop");

        calculateTotalSum calculateTotalSum  = new calculateTotalSum() ;
         int sum = calculateTotalSum.calculateTotalSum(orderList) ;
        System.out.println("Total amount of price  " + sum  );

        sendEmailNotification sendEmailNotification  = new sendEmailNotification() ;
        sendEmailNotification.sendEmailNotification("Congratulation you order has been palced ");

    }
}