package ra.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class Oder implements Serializable {
    private User orderUser;
    private List<CartItem> oderCart;
    private String oderStatus;
    private String Feedback;

    public Oder() {
    }

    public Oder(User orderUser, List<CartItem> oderCart, String oderStatus, String feedback) {
        this.orderUser = orderUser;
        this.oderCart = oderCart;
        this.oderStatus = oderStatus;
        Feedback = feedback;
    }

    public User getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public List<CartItem> getOderCart() {
        return oderCart;
    }

    public void setOderCart(List<CartItem> oderCart) {
        this.oderCart = oderCart;
    }

    public String getOderStatus() {
        return oderStatus;
    }

    public void setOderStatus(String oderStatus) {
        this.oderStatus = oderStatus;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Oder.class.getSimpleName() + "[", "]")
                .add("oderUser=" + orderUser)
                .add("oderCart=" + oderCart)
                .add("oderStatus='" + oderStatus + "'")
                .add("Feedback='" + Feedback + "'")
                .toString();
    }
}
