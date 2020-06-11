package codegym.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private  int id ;
    private  User user;
    private List<Item> items = new ArrayList<>();
    private int status;
    private String date;
    public Order() {
    }

    public Order( User user, List<Item> items, int status) {
        this.user = user;
        this.items = items;
        this.status = status;
    }

    public void addItem(Item item) {

        for (Item _item :  items) {
            int id = _item.getProduct().getId();
            if (id == item.getProduct().getId()) {
                increaseQuantityByProductId(id);
                return;
            }
        }
        this.items.add(item);
    }

    private void increaseQuantityByProductId(int productId) {
        for (Item item :
                items) {
            if (item.getProduct().getId() == productId) {
                item.setQuantity(item.getQuantity()+1);
            }
        }
    }
    public void deleteItemByProductId(int productId) {
        for (Item item : items) {
            if (item.getProduct().getId() == productId) {
                items.remove(item);
            }
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
