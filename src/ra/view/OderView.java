package ra.view;

import ra.controller.oder.ControllerOder;
import ra.model.entity.*;
import ra.model.service.cart.CartServiceIMPL;

import java.util.List;

import static ra.config.Config.scanner;

public class OderView {
    private static ControllerOder controllerOder = new ControllerOder();
    private static User userlogin = new UserView().getUserLogin();

    public static void showOder() {
        Oder oder = controllerOder.findById(userlogin.getUserId());
        List<CartItem> cartItemList = oder.getOderCart();
        float Total = 0;
        for (CartItem item : cartItemList) {
            Total += item.getProduct().getProductPrice() * item.getQuantity();
        }
        System.out.printf("Customer: %s\n" +
                        "Address: %s\n" +
                        "Phone: %s\n",
                oder.getOrderUser().getName(),
                oder.getOrderUser().getAddress(),
                oder.getOrderUser().getPhone());
        for (int i = 0; i < cartItemList.size(); i++) {
            System.out.println("Product " + (i + 1) + ": " + cartItemList.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItemList.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItemList.get(i).getQuantity());
        }
        System.out.println("Total Oder: " + Total);
    }

    public void showOderMenu() {
        boolean check = true;
        while (check) {
            System.out.println("********** YOUR Oder **********");
            System.out.println("1. Show Oder");
            System.out.println("2. Pay Now");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showOder();
                    break;
                case 2:
                    showOder();
                    payNow();
                case 3:
                    check = false;
                    break;
                default:
                    System.err.println("err");
                    break;
            }
        }
    }
    public void payment(){
        boolean check = true;
        while (check)
        System.out.println("********** PAYMENT **********");
        System.out.println("1. Pay Now");
        System.out.println("2. Exit");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                payNow();
                System.out.println(" Thank you So Much, See you again !");
                break;
            case 2:
                check = false;
                break;
            default:
                System.err.println("error");
                break;
        }
    }
    public void createOder() {
        Oder oder = new Oder();
        oder.setOrderUser(userlogin);
        Cart mycart = new CartServiceIMPL().findById(userlogin.getUserId());
        List<CartItem> cartItems = mycart.getCartItem();
        oder.setOderCart(cartItems);
        oder.setOderStatus("delivery");
        oder.setFeedback("");
        controllerOder.save(oder);
    }
    public void payNow(){
        Oder oder = controllerOder.findById(userlogin.getUserId());
        System.out.println("Enter the customer feedback: ");
        String feedback = scanner.nextLine();
        Oder oderHistory = new Oder(oder.getOrderUser(), oder.getOderCart(),"completed",feedback);
        System.out.println("oder history-->"+ oderHistory);
        controllerOder.saveOderHistory(oderHistory);
        new CartView().deleteAllCart();
        controllerOder.deleteOder(userlogin.getUserId());
        System.out.println("thank you very much, see you again! ");
        new Main().viewUser();
    }
    public void showOderHistory(){
        Oder oder = controllerOder.showOderHistory(userlogin.getUserId());
        List<CartItem> cartItems = oder.getOderCart();
        float Total = 0;
        for (CartItem item : cartItems) {
            Total += item.getProduct().getProductPrice() * item.getQuantity();
        }
        System.out.printf("Customer: %s\n" +
                        "Address: %s\n" +
                        "Phone: %s\n",
                oder.getOrderUser().getName(),
                oder.getOrderUser().getAddress(),
                oder.getOrderUser().getPhone());
        for (int i = 0; i < cartItems.size(); i++) {
            System.out.println("Product " + (i + 1) + ": " + cartItems.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItems.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItems.get(i).getQuantity());
        }
        System.out.println("Total Oder: " + Total);
    }
    public void oderManagement(){
        boolean check = true;
        while (check) {
            System.out.println("********** ODER MANAGEMENT **********");
            System.out.println("1. Show Oder History");
            System.out.println("2. Exit");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    showAllOder();
                    break;
                case 2:
                    check = false;
                    break;
                default:
                    System.err.println("error");
                    break;
            }
        }
    }
    public void showAllOder(){
        List<Oder> oderList = controllerOder.showAllOderHistory();
        for (Oder oder : oderList) {
            List<CartItem> cartItems = oder.getOderCart();
            float Total = 0;
            for (CartItem item : cartItems) {
                Total += item.getProduct().getProductPrice() * item.getQuantity();
            }
            System.out.printf("Customer: %s\n" +
                            "Address: %s\n" +
                            "Phone: %s\n",
                    oder.getOrderUser().getName(),
                    oder.getOrderUser().getAddress(),
                    oder.getOrderUser().getPhone());
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.println("Product " + (i + 1) + ": " + cartItems.get(i).getProduct().getProductName() + ",  " + "Price: " + cartItems.get(i).getProduct().getProductPrice() + ",  " + "Quantity: " + cartItems.get(i).getQuantity());
            }
            System.out.println("Feedback: "+ oder.getFeedback());
            System.out.println("ODER Status: "+ oder.getOderStatus());
            System.out.println("Total Oder: " + Total);
            System.out.println("-----------------------------------------");
        }
    }
}
